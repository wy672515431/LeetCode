package bytedance.数组;

public class 除自身以外数组的乘积 {
    /**
     * 空间复杂度为O(n)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        int[] preMult = new int[nums.length];
        int[] postMult = new int[nums.length];
        preMult[0] = 1;
        postMult[nums.length - 1] = 1;
        for(int i = 1; i < nums.length; i++) {
            preMult[i] = nums[i - 1] * preMult[i - 1];
        }
        for(int i = nums.length - 2; i >= 0; i--) {
            postMult[i] = nums[i + 1] * postMult[i + 1];
        }
        for(int i = 0; i < nums.length; i++) {
            ans[i] = postMult[i] * preMult[i];
        }
        return ans;
    }

    /**
     * 空间复杂度为O(1)
     * 我们将ans数组首先作为前缀乘积数组
     * @param nums
     * @return
     */
    public int[] productExceptSelf1(int[] nums) {
        int length = nums.length;
        int[] ans = new int[length];
        ans[0] = 1;
        for(int i = 1; i < length; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }
        //R为右侧所有元素的乘积
        int R = 1;
        for(int i = length - 1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R = R * nums[i];
        }

        return ans;
    }

}
