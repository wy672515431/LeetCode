package CF.CF_974_DIV3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            String[] input = bf.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int ans = 0;
            int gold = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] >= k) {
                    gold += arr[i];
                } else if (arr[i] == 0 && gold > 0) {
                    ans++;
                    gold--;
                }
            }
            System.out.println(ans);
        }
        bf.close();
    }
}
