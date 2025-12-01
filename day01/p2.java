import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class p2 {
    public static void main(String[] args) throws IOException {
        Integer position = 50, dials = 100, result = 0;
        var lines = Files.readAllLines(Path.of(args[0]));

        for (var line : lines) {
            Integer delta = Integer.parseInt(line.substring(1));
            Integer direction = (line.charAt(0) == 'L') ? -1 : 1;

            result += delta / dials;
            delta %= dials;

            if (position != 0 && (position + delta * direction >= dials || position + delta * direction <= 0))
                result++;
            position = Math.floorMod(position + direction * delta, dials);
        }

        System.out.println(result);
    }
}