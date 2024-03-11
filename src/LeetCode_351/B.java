package LeetCode_351;

public class B {
    public int makeTheIntegerZero(int num1, int num2) {
        int k = 1;
        long diff = num1 - k * (long) num2;
        int ans = -1;
        while (diff > 0) {
            // count the bit
            long temp = diff;
            int count = 0;
            while (temp != 0) {
                count++;
                temp = temp & (temp - 1);
            }
            // 最少次数是count,最多次数是diff,必须满足temp >= k
            if (count <= k && temp >= k) {
                return k;
            }
            k++;
            diff = num1 - k * (long) num2;
        }
        return ans;
    }
}
