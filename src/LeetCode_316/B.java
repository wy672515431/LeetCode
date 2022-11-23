package LeetCode_316;

public class B {
    public int subarrayGCD(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int gcd = nums[i];
            for (int j = i; j < nums.length; j++) {
                if (gcd(gcd, nums[j]) == k) {
                    ans++;
                } else if (gcd(gcd, nums[j]) % k != 0) {
                    break;
                }
                gcd = gcd(gcd, nums[j]);
            }
        }
        return ans;
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
