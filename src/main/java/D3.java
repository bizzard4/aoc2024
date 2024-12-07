import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D3 {
    public static void main(String[] args) {
        InputStream is = D1.class.getClassLoader().getResourceAsStream("d3b.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        var lines = reader.lines().toList();

        StringBuilder all = new StringBuilder();
        for (String l : lines) { all.append(l); }

        Pattern pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
        long acc = 0;
        var count = 0;
        Matcher matcher = pattern.matcher(all.toString());
        while (matcher.find()) {
            String mul = matcher.group();
            System.out.println(mul);
            count++;

            var ints = Arrays.stream(mul.substring(4, mul.length() - 1).split(",")).map(Integer::valueOf).toList();
            acc = acc + (ints.get(0) * ints.get(1));
        }
        System.out.println(count);
        System.out.println(acc);
    }
}
