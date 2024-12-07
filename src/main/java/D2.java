import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class D2 {
    public static void main(String[] args) {
        InputStream is = D1.class.getClassLoader().getResourceAsStream("d2.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Stream<String> lines = reader.lines();

        List<Report> reports = new ArrayList<>();
        for (String l : lines.toList()) {
            List<Integer> splitted = Arrays.stream(l.split("\\s+")).map(Integer::valueOf).toList();
            reports.add(new Report(splitted));
        }

        var safeCnt = 0;
        for (Report r: reports) {
            if (r.isSafe()) {
                safeCnt++;
            } else {
                // We try to remove all the level one-by-one and see if one of the alternative report is safe
                var anySafe = false;
                for (int i = 0; i < r.levels.size(); i++) {
                    var arrayCopy = new ArrayList<>(r.levels);
                    arrayCopy.remove(i);
                    Report alternativeReport = new Report(arrayCopy);
                    if (alternativeReport.isSafe()) anySafe = true;
                }
                if (anySafe) safeCnt++;
            }
        }
        System.out.println(safeCnt);
    }

    record Report(List<Integer> levels) {
        // Number of level per report can vary with a minimum of 5
        public boolean isSafe() {
            // Shortcut for strictly increasing/decreasing
            // Sort nlogn and compare with original list
            var ascendingCmp = levels.stream().sorted().toList();
            var descendingCmp = ascendingCmp.reversed();

            var stricklyUp = levels.equals(ascendingCmp);
            var stricklyDown = levels.equals(descendingCmp);

            var maxGap = 0;
            var minGap = Integer.MAX_VALUE;
            for (int i = 0; i < levels.size()-1; i++) {
                var iNext = i+1;
                var gap = Math.abs(levels.get(i) - levels.get(iNext));
                if (gap > maxGap) { maxGap = gap; }
                if (gap < minGap) { minGap = gap; }
            }
            var isSafe = (stricklyUp || stricklyDown) && (minGap > 0) && (maxGap < 4);
            return isSafe;
        }
    }
}
