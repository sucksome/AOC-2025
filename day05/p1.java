import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class p1 {
    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            int result = 0;
            ArrayList<ArrayList<Long>> ranges = new ArrayList<>();

            String line;
            boolean ingredients = false;
            while ((line = input.readLine()) != null) {
                if (ingredients) {
                    long id = Long.parseLong(line);
                    for (var range : ranges) {
                        if (id >= range.getFirst() && id <= range.getLast()) {
                            result++;
                            break;
                        }
                    }
                } else {
                    if (line.isEmpty()) {
                        ingredients = true;
                        continue;
                    }

                    ArrayList<Long> range = new ArrayList<>();
                    for (var num : line.split("-"))
                        range.add(Long.parseLong(num));
                    ranges.add(range);
                }
            }

            System.out.println(result);
        }
    }
}
