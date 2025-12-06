import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class p1 {

    public static long op(String symbol, long a, long b) {
        if (symbol.equals("+"))
            return a + b;
        return a * b;
    }

    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            String line;
            long result = 0;
            ArrayList<String[]> worksheet = new ArrayList<>();

            while ((line = input.readLine()) != null)
                worksheet.add(line.trim().split("\\s+"));

            for (int i = 0; i < worksheet.getFirst().length; i++) {
                String symbol = worksheet.getLast()[i];
                long cur = symbol.equals("*") ? 1 : 0;

                for (int j = 0; j < worksheet.size() - 1; j++)
                    cur = op(symbol, cur, Long.parseLong(worksheet.get(j)[i]));

                result += cur;
            }

            System.out.println(result);
        }
    }
}
