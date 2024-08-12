package bytedance.数组;

public class 加油站 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        int start = 0;
        int total = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            // 这里保证了[start, i - 1]中的任意一个点都不行
            // 因为sum在其上面是递减的
            if (sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        return total >= 0 ? start : -1;
    }
}
