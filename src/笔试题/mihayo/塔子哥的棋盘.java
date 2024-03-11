package 笔试题.mihayo;

import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class 塔子哥的棋盘 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int xa = scanner.nextInt();
        int ya = scanner.nextInt();
        int xb = scanner.nextInt();
        int yb = scanner.nextInt();
        int xc = scanner.nextInt();
        int yc = scanner.nextInt();
        long ans = 0;
        // Math.min(|xa - xb|, n - |xa - xb|)
        ans += Math.min(Math.abs((long) xa - xb), n - Math.abs((long) xa - xb));
        ans += Math.min(Math.abs((long) ya - yb), m - Math.abs((long) ya - yb));
        ans += Math.min(Math.abs((long) xb - xc), n - Math.abs((long) xb - xc));
        ans += Math.min(Math.abs((long) yb - yc), m - Math.abs((long) yb - yc));
        System.out.println(ans);
    }
}
