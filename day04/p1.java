import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class p1 {

    static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };

    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            int result = 0;
            ArrayList<String> grid = new ArrayList<>();

            String row;
            while ((row = input.readLine()) != null)
                grid.add(row);

            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).length(); j++) {
                    if (grid.get(i).charAt(j) != '@')
                        continue;

                    int adj = 0;
                    for (int d = 0; d < 8; d++) {
                        int ni = i + dir[d][0], nj = j + dir[d][1];
                        if (ni < 0 || ni >= grid.size() || nj < 0 || nj >= grid.get(i).length())
                            continue;
                        if (grid.get(ni).charAt(nj) == '@')
                            adj++;
                    }
                    if (adj < 4)
                        result++;
                }
            }

            System.out.println(result);
        }
    }
}
