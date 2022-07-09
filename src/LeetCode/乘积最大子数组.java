package LeetCode;

public class 乘积最大子数组 {
    public int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int curMaxProduct = nums[0];
        int curMinProduct = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tem = curMaxProduct;
                curMaxProduct = Math.max(curMinProduct * nums[i], nums[i]);
                curMinProduct = Math.min(tem * nums[i], nums[i]);
            } else {
                int tem = curMaxProduct;
                curMaxProduct = Math.max(curMaxProduct * nums[i], nums[i]);
                curMinProduct = Math.min(curMinProduct * nums[i], nums[i]);
            }
            maxProduct = Math.max(maxProduct, curMaxProduct);
        }
        return maxProduct;
    }
}
