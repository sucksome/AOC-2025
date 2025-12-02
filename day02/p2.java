import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class p2 {

    private static void processPrefixSize(String l, String r, int size) {
        long start, end, low, high;
        low = Long.parseLong(l);
        high = Long.parseLong(r);
        start = size > 0 ? Long.parseLong(l.substring(0, Math.min(size, l.length()))) : 1;
        end = Long.parseLong(r.substring(0, Math.min(size + 1, r.length())));

        for (long val = start; val <= end; val++) {
            long concat = Long.parseLong(val + "" + val);

            if (concat > high)
                break;

            while (concat <= high) {
                if (concat >= low)
                    invalidIds.add(concat);
                concat = Long.parseLong(concat + "" + val);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            String text = input.readLine();

            for (var range : text.split(",")) {
                int idx = range.indexOf('-');
                String l = range.substring(0, idx), r = range.substring(idx + 1);

                for (int sz = 0; sz <= (r.length() + 1) / 2; sz++)
                    processPrefixSize(l, r, sz);
            }

            System.out.println(invalidIds.stream().mapToLong(Long::longValue).sum());
        }
    }

    private static HashSet<Long> invalidIds = new HashSet<>();
}
