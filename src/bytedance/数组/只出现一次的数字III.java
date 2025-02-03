package bytedance.数组;

public class 只出现一次的数字III {
    public int[] singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        // 获得最右边的1
        int mask = res & -res;
        int[] ans = new int[2];
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        ans[0] = a;
        ans[1] = b;
        return ans;
    }
}
