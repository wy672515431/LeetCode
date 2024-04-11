package 美团笔试;

import java.util.Scanner;

public class 小红结账 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        long[] ans = new long[m];
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            int c = scanner.nextInt();
            int average = (c - 1) / k + 1;
            for (int j = 0; j < k - 1; j++) {
                int index = scanner.nextInt();
                ans[index - 1] += average;
            }
        }
        for (int i = 0; i < m; i++) {
            System.out.print(ans[i]);
            if (i != m - 1) {
                System.out.print(' ');
            }
        }
    }
}
