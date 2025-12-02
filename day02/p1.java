import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class p1 {
    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            long sum = 0;
            String text = input.readLine();

            for (var range : text.split(",")) {
                int idx = range.indexOf('-');
                String l = "0" + range.substring(0, idx), r = range.substring(idx + 1);
                long low = Long.parseLong(l), high = Long.parseLong(r);

                long start = Long.parseLong(l.substring(0, l.length() / 2));
                long end = Long.parseLong(r.substring(0, (r.length() + 1) / 2));

                for (long val = start; val <= end; val++) {
                    long concat = Long.parseLong(val + "" + val);
                    if (concat <= high && concat >= low)
                        sum += concat;
                }
            }

            System.out.println(sum);
        }
    }
}
