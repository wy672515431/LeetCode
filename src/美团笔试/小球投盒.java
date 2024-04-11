package 美团笔试;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 小球投盒 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Set<Integer> include = new HashSet<>();
        Set<Integer> exclude = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int t = scanner.nextInt();
            int x = scanner.nextInt();
            if (t == 1) {
                include.add(x);
            } else if (t == 2) {
                exclude.add(x);
            }

            if (exclude.isEmpty()) {
                if (include.size() == n) {
                    System.out.println(i + 1);
                    return;
                }
            } else if (exclude.size() == 1) {
                for (int num : exclude) {
                    if (include.contains(num)) {
                        System.out.println(i + 1);
                        return;
                    }
                }
            } else {
                System.out.println(i + 1);
                return;
            }
        }

        System.out.println(-1);
    }
}
