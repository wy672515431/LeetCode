package CF.CF_984_DIV3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(bf.readLine());
            int[] a = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                if (Math.abs(a[i] - a[i - 1]) != 5 && Math.abs(a[i] - a[i - 1]) != 7) {
                    flag = false;
                    break;
                }
            }
            System.out.println(flag ? "YES" : "NO");
        }
        bf.close();
    }
}
