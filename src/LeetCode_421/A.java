package LeetCode_421;

public class A {
    public long maxScore(int[] nums) {
        int n = nums.length;
        long res = 0;
        long lcm = 1;
        long gcd = 0;
        for (int i = 0; i < n; i++) {
            gcd = gcd(gcd, nums[i]);
            // 先求两个数的最小公倍数，再以此迭代
            lcm = lcm * nums[i] / gcd(lcm, nums[i]);
        }
        res = Math.max(res, gcd * lcm);
        for (int i = 0; i < n; i++) {
            lcm = 1;
            gcd = 0;
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    gcd = gcd(gcd, nums[j]);
                    lcm = lcm * nums[j] / gcd(lcm, nums[j]);
                }
            }
            res = Math.max(res, gcd * lcm);
        }
        return res;
    }

    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
