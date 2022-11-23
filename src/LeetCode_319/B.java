package LeetCode_319;

public class B {
    public int subarrayLCM(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int res = 1;
            for (int j = i; j < nums.length; j++) {
                res = lcm(res, nums[j]);
                if (k % res != 0) {
                    break;
                }
                if (res == k) {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    /**
     * get the gcd of a and b
     *
     * @param a num1
     * @param b num2
     * @return the gcd of a and b
     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * get the lcm of a and b
     * a * b = lcm(a, b) * gcd(a, b)
     *
     * @param a num1
     * @param b num2
     * @return the lcm of a and b
     */
    public int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}


