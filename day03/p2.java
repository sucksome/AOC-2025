import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;

public class p2 {

    private static long greatestSubSeq(String s, int len) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            while (!st.empty() && st.size() + n - i > len && st.peek() < num)
                st.pop();
            st.push(num);
        }
        while (st.size() > len)
            st.pop();

        long ans = 0;
        for (long i : st) {
            ans *= 10;
            ans += i;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        long sum = 0;
        try (var input = Files.newBufferedReader(Path.of(args[0]))) {
            String line;
            while ((line = input.readLine()) != null)
                sum += greatestSubSeq(line, 12);
            System.out.println(sum);
        }
    }
}