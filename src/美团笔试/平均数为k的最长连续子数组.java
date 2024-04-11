package 美团笔试;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 平均数为k的最长连续子数组 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt() - k;
        }
        int ans = -1;
        // [l .. r] (pre[r + 1] - pre[l]) / (r - l + 1) == k
        // 平均数为k需满足以下等式
        // (pre[r + 1] - k * length) = pre[l]
        // 将每个数字减k, 则必须满足以下等式， 平均值为k变为平均值为0
        // pre[r + 1] = pre[l]
        long[] preSum = new long[n + 1];
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
            // 查询前面是否有pre[l] == pre[i + 1]
            if (map.containsKey(preSum[i + 1])) {
                ans = Math.max(ans, i - map.get(preSum[i + 1]));
            } else {
                map.put(preSum[i + 1], i);
            }
        }
        System.out.println(ans);
    }
}
