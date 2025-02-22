import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;

public class D4 {
    public static void main(String[] args) {
        InputStream is = D1.class.getClassLoader().getResourceAsStream("d4.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        var lines = reader.lines().toList();
        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            grid[i] = new char[line.length()];
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        int sumOfXmas = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sumOfXmas += lookup(grid, i, j);
            }
        }

        System.out.println("DONE " + sumOfXmas);
    }

    public static String LOOKUP_VALUE = "XMAS";

    interface Director {
        int nextI(int i, int c);
        int nextJ(int j, int c);
    }

    public static boolean isDirectionGood(char[][] grid, int i, int j, Director director) {
        boolean directionGood = true;
        for (int c = 0; c < LOOKUP_VALUE.length(); c++) {
            if (inBoundaries(grid, director.nextI(i, c), director.nextJ(j, c))) {
                if (grid[director.nextI(i, c)][director.nextJ(j, c)] != LOOKUP_VALUE.charAt(c)) {
                    directionGood = false;
                }
            } else {
                directionGood = false;
            }
        }
        return directionGood;
    }

    public static boolean isCrossGood(char[][] grid, int i, int j, char a, char b, char c, char d) {
        boolean validCross = true;
        // Top left
        if (inBoundaries(grid, i-1, j-1)) {
            if (grid[i-1][j-1] != a) {
                validCross = false;
            }
        } else {
            validCross = false;
        }

        // Top right
        if (inBoundaries(grid, i-1, j+1)) {
            if (grid[i-1][j+1] != b) {
                validCross = false;
            }
        } else {
            validCross = false;
        }

        // Bottom left
        if (inBoundaries(grid, i+1, j-1)) {
            if (grid[i+1][j-1] != c) {
                validCross = false;
            }
        } else {
            validCross = false;
        }

        // Bottom right
        if (inBoundaries(grid, i+1, j+1)) {
            if (grid[i+1][j+1] != d) {
                validCross = false;
            }
        } else {
            validCross = false;
        }

        return validCross;
    }

    public static int lookup(char[][] grid, int i, int j) {
        int total = 0;

        // From center
        boolean hasValidCross = false;
        if (grid[i][j] == 'A') {
            if (isCrossGood(grid, i, j, 'M', 'S', 'M', 'S')) {
                hasValidCross = true;
            } else if  (isCrossGood(grid, i, j, 'M', 'M', 'S', 'S')) {
                hasValidCross = true;
            } else if  (isCrossGood(grid, i, j, 'S', 'M', 'S', 'M')) {
                hasValidCross = true;
            } else if  (isCrossGood(grid, i, j, 'S', 'S', 'M', 'M')) {
                hasValidCross = true;
            }
        }
        if (hasValidCross) {
            total++;
        }

        return total;
    }

    public static boolean inBoundaries(char[][] grid, int i, int j) {
        return ((i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[i].length));
    }
}
