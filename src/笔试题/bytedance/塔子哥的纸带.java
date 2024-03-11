package 笔试题.bytedance;

import java.util.Arrays;
import java.util.Scanner;

public class 塔子哥的纸带 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] black = new int[3];
            for (int i = 0; i < 3; i++) {
                black[i] = scanner.nextInt() - 1;
            }
            if (n < k * 3) {
                System.out.println(-1);
                t--;
                continue;
            }
            Arrays.sort(black);
            // black[0] + k <= black[1]
            // black[1] + k <= black[2]
            // (black[2] + k) % n <= black[0]
            // 分配 k * 3的空间
            // 如果现在black[2] - black[0] + 1的长度小于k * 3我们就要对其进行扩充
            int ans = 0;
            int diff1 = black[1] - black[0] - k;
            int diff2 = black[2] - black[1] - k;
            int diff3 = n - (black[2] - black[0]) - k;
            ans += diff1 < 0 ? -diff1 : 0;
            ans += diff2 < 0 ? -diff2 : 0;
            ans += diff3 < 0 ? -diff3 : 0;
            System.out.println(ans);
            t--;
        }
    }
}
