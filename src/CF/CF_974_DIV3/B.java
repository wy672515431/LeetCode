package CF.CF_974_DIV3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            String[] input = bf.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            // valid years [n - k + 1, n]
            if (k % 2 == 1) {
                // 奇数
                if ((n - k + 1) % 2 == (k / 2) % 2) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            } else {
                if ((k / 2) % 2 == 1) {
                    System.out.println("No");
                } else {
                    System.out.println("Yes");
                }
            }
        }
        bf.close();
    }
}
