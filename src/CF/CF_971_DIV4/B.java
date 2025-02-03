package CF.CF_971_DIV4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            String[] input = new String[n];
            for (int i = 0; i < n; i++) {
                input[i] = bufferedReader.readLine();
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    if (input[i].charAt(j) == '#') {
                        System.out.print(j + 1);
                        System.out.print(" ");
                        break;
                    }
                }
            }
            System.out.println();
        }
        bufferedReader.close();
    }
}
