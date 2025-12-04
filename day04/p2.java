import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class p2 {

    static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };

    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            int result = 0, cur;
            ArrayList<String> grid = new ArrayList<>();

            String line;
            while ((line = input.readLine()) != null)
                grid.add(line);

            do {
                cur = 0;
                ArrayList<String> newGrid = new ArrayList<>();
                for (int i = 0; i < grid.size(); i++) {
                    StringBuilder row = new StringBuilder();
                    for (int j = 0; j < grid.get(i).length(); j++) {
                        if (grid.get(i).charAt(j) != '@') {
                            row.append('.');
                            continue;
                        }

                        int adj = 0;
                        for (int d = 0; d < 8; d++) {
                            int ni = i + dir[d][0], nj = j + dir[d][1];
                            if (ni < 0 || ni >= grid.size() || nj < 0 || nj >= grid.get(i).length())
                                continue;
                            if (grid.get(ni).charAt(nj) == '@')
                                adj++;
                        }
                        if (adj < 4) {
                            cur++;
                            row.append('.');
                        } else
                            row.append('@');
                    }
                    newGrid.add(row.toString());
                }
                result += cur;
                grid = newGrid;
            } while (cur > 0);

            System.out.println(result);
        }
    }
}
