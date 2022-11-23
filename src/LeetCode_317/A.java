package LeetCode_317;

public class A {
    public int averageValue(int[] nums) {
        int ans = 0;
        int cnt = 0;
        for (int num : nums) {
            if (num % 2 == 0 && num % 3 == 0) {
                ans += num;
                cnt += 1;
            }
        }
        return (cnt == 0) ? 0 : ans / cnt;
    }
}
