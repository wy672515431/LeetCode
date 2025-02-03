package CF.CF_971_DIV4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        while (t-- > 0) {
            String[] input = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int k = Integer.parseInt(input[2]);
            int xTimes = (x + k - 1) / k;
            int yTimes = (y + k - 1) / k;
            if (yTimes >= xTimes) {
                System.out.println(2 * yTimes);
            } else {
                System.out.println(2 * xTimes - 1);
            }
        }
        bufferedReader.close();
    }
}
