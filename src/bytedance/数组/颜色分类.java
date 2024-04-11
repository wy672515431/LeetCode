package bytedance.数组;

public class 颜色分类 {
    public void sortColors(int[] nums) {
        int n = nums.length, red = 0, blue = n - 1;
        // 2 0 2 1 1 0
        // 0 0 2 1 1 2
        // 0 0 1 1 2 2
        for (int i = 0; i <= blue; i++) {
            while (i <= blue && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[blue];
                nums[blue] = temp;
                blue--;
            }

            // 此时可能为0
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[red];
                nums[red] = temp;
                red++;
            }
        }
    }
}
