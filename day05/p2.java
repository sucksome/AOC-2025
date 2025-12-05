import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class p2 {
    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            long result = 0;
            ArrayList<ArrayList<Long>> ranges = new ArrayList<>();

            String line;
            while ((line = input.readLine()) != null) {
                if (line.isEmpty())
                    break;

                ArrayList<Long> range = new ArrayList<>();
                for (var num : line.split("-"))
                    range.add(Long.parseLong(num));
                ranges.add(range);
            }

            ranges.sort(Comparator.comparing((ArrayList<Long> r) -> r.getFirst()).thenComparing(r -> r.getLast()));

            ArrayList<ArrayList<Long>> mergedRanges = new ArrayList<>();
            for (var range : ranges) {
                if (mergedRanges.isEmpty() || mergedRanges.getLast().getLast() < range.getFirst())
                    mergedRanges.add(range);
                else if (mergedRanges.getLast().getLast() < range.getLast())
                    mergedRanges
                            .add(new ArrayList<Long>(List.of(mergedRanges.getLast().getLast() + 1, range.getLast())));
                else
                    continue;
                result += mergedRanges.getLast().getLast() - mergedRanges.getLast().getFirst() + 1;
            }

            System.out.println(result);
        }
    }
}
