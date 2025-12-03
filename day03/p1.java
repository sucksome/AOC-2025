import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class p1 {

    public static void main(String[] args) throws IOException {
        int sum = 0;
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            String line;
            while ((line = input.readLine()) != null) {
                int firstDigit = line.substring(0, line.length() - 1).chars().map(c -> c - '0').max().getAsInt();
                int secondDigit = line.substring(line.indexOf(firstDigit + '0') + 1).chars().map(c -> c - '0').max()
                        .getAsInt();
                sum += firstDigit * 10 + secondDigit;
            }
            System.out.println(sum);
        }
    }
}