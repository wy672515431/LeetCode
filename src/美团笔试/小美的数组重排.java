package 美团笔试;

import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 小美的数组重排 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            List<Integer> a = new ArrayList<>();
            List<Integer> b = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                a.add(scanner.nextInt());
            }
            for (int j = 0; j < n; j++) {
                b.add(scanner.nextInt());
            }
            Collections.sort(a);
            Collections.sort(b, Collections.reverseOrder());
            // a的最小值和b的最大值相加必须在[1, m]中，如果大于m，则说明b的最大值太大了，如果小于1，则说明a的最小值太小了
            // 证明正确性，假设ai + bi不满足，我们能否找到另外一个aj + bi或者 ai + bj满足呢？
            // 如果小于，则我们需要找到一个aj(j > i, aj > ai)，但这个ai会与一个更小的bk组合，导致不满足
            // 如果大于，我们需要找到一个bj (j > i, bj < bi), 但这个bi会与一个更大的ak组合，导致不满足
            // ai + bi 在区间
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                int num1 = a.get(j);
                int num2 = b.get(j);
                if (num1 + num2 < 1 || num1 + num2 > m) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
