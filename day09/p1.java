import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class p1 {

    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            List<List<Integer>> redTiles = new ArrayList<>();
            String line;
            while ((line = input.readLine()) != null) {
                String[] coords = line.split(",");
                redTiles.add(List.of(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
            }

            long result = 0;
            for (int i = 0; i < redTiles.size(); i++) {
                for (int j = i + 1; j < redTiles.size(); j++) {
                    result = Math.max(result, (1L + Math.abs(redTiles.get(i).get(0) - redTiles.get(j).get(0)))
                            * (1L + Math.abs(redTiles.get(i).get(1) - redTiles.get(j).get(1))));
                }
            }

            System.out.println(result);
        }
    }
}