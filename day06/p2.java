import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class p2 {

    public static long op(char symbol, long a, long b) {
        if (symbol == '+')
            return a + b;
        return a * b;
    }

    public static void main(String[] args) throws IOException {
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            String line;
            long result = 0;
            ArrayList<String> worksheet = new ArrayList<>();

            while ((line = input.readLine()) != null)
                worksheet.add(line.replace(' ', '0'));

            ArrayList<Long> nums = new ArrayList<>();
            for (int col = worksheet.getFirst().length() - 1; col >= 0; col--) {
                long cur = 0;
                for (int row = 0; row < worksheet.size() - 1; row++) {
                    cur *= 10;
                    cur += worksheet.get(row).charAt(col) - '0';
                }

                if (cur == 0)
                    continue;
                while (cur % 10 == 0)
                    cur /= 10;
                nums.add(cur);

                char symbol = worksheet.getLast().charAt(col);
                if (symbol != '0') {
                    long num = symbol == '*' ? 1 : 0;
                    while (nums.size() > 0) {
                        num = op(symbol, num, nums.getLast());
                        nums.removeLast();
                    }
                    result += num;
                }
            }
            System.out.println(result);
        }
    }
}
