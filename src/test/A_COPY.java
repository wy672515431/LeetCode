package test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class A_COPY {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        String[] ss = scanner.nextLine().split(" ");
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(ss[i]));
        }
        long res = 0;
        while (pq.size() >= m) {
            int[] temp = new int[m];
            for (int i = 0; i < m; i++) {
                temp[i] = pq.poll();
            }
            res += temp[m - 1];
            for (int i = 0; i < m - 1; i++) {
                if (temp[i] - temp[m - 1] > 0) {
                    pq.offer(temp[i] - temp[m - 1]);
                }
            }
        }
        System.out.println(res);


    }
}
