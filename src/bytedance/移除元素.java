package bytedance;

public class 移除元素 {
    /**
     * 除去在nums数组中值为val的数
     *
     * @param nums
     * @param val
     * @return 返回移除后数组的长度
     */
    public static int removeElement(int[] nums, int val) {
        int i = 0, j = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[i] == val) {
                // remove
            } else {
                nums[j] = nums[i];
                j++;
            }
            i++;
        }
        return j;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(nums, 2));
    }
}
