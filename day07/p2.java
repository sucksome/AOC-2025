import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class p2 {
    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            String line;
            HashMap<Integer, Long> beams = new HashMap<>();
            while ((line = input.readLine()) != null) {
                HashMap<Integer, Long> newBeams = new HashMap<>();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == 'S')
                        beams.put(i, 1L);
                    else if (c == '^') {
                        if (beams.containsKey(i)) {
                            newBeams.put(i - 1, beams.get(i) + newBeams.getOrDefault(i - 1, 0L));
                            newBeams.put(i + 1, beams.get(i) + newBeams.getOrDefault(i + 1, 0L));
                            beams.remove(i);
                        }
                    }
                }

                newBeams.forEach((k, v) -> beams.merge(k, v, Long::sum));
            }

            long result = 0;
            for (var beam : beams.values())
                result += beam;

            System.out.println(result);
        }
    }
}
