import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class p1 {
    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            long result = 0;
            String line;
            HashSet<Integer> beams = new HashSet<>();
            while ((line = input.readLine()) != null) {
                HashSet<Integer> newBeams = new HashSet<>();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == 'S')
                        beams.add(i);
                    else if (c == '^') {
                        if (beams.contains(i)) {
                            result++;
                            newBeams.add(i - 1);
                            newBeams.add(i + 1);
                            beams.remove(i);
                        }
                    }
                }

                beams.addAll(newBeams);
            }

            System.out.println(result);
        }
    }
}
