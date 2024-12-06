import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class d1 {
    public static void main(String[] args) {
        InputStream is = d1.class.getClassLoader().getResourceAsStream("d1.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Stream<String> lines = reader.lines();
        List<Integer> colA = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (String l : lines.toList()) {
            String[] splitted = l.split("\\s+");
            colA.add(Integer.valueOf(splitted[0]));

            var colBInt = Integer.valueOf(splitted[1]);
            if (countMap.containsKey(colBInt)) {
                countMap.put(colBInt, countMap.get(colBInt) + 1);
            } else {
                countMap.put(colBInt, 1);
            }
        }

        List<Integer> distance = new ArrayList<>();
        for (Integer i : colA) {
            var multi = countMap.getOrDefault(i, 0);
            var toAdd = i * multi;
            distance.add(toAdd);
        }

        var sum = distance.stream().mapToInt(i -> i).sum();
        System.out.println(sum);
    }
}
