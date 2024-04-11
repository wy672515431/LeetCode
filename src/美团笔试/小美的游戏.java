package 美团笔试;

import java.util.Arrays;
import java.util.Scanner;

public class 小美的游戏 {
    public static void main(String[] args) {
        int mod = (int)1e9 + 7;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        int init = arr[n - 1];
        for (int i = n - 2; i >= n - k - 1; i--) {
            init = (int)(((long) init * arr[i]) % mod);
        }
        int ans = (init + k) % mod;
        for (int i = n - k - 2; i >= 0; i--) {
            ans = (ans + arr[i]) % mod;
        }
        System.out.println(ans);
    }
}
