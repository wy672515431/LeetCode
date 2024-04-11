package 美团笔试;

import java.util.Scanner;

public class 小美种果树 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt(), y = scanner.nextInt(), z = scanner.nextInt();
        // 一个阶段的成长值是 3x + y
        long grows = (long) x * 3 + y;
        long ans = 0;
        ans += (z / grows) * 3;
        int left = (int)(z % grows);
        if (left == 0) {
            System.out.println(ans);
            return;
        }
        if (left <= x + y) {
            ans += 1;
        } else if (left <= (long) x * 2 + y) {
            ans += 2;
        } else if (left < (long) x * 3 + y) {
            ans += 3;
        }
        System.out.println(ans);
    }
}
