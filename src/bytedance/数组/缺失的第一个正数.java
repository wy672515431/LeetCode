package bytedance.数组;

public class 缺失的第一个正数 {
    public int optimizedFirstMissingPositive(int[] nums) {
        // 我们希望数组的第x - 1个元素为x
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 交换，并确保交换前当前位置不是正确的
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        // 对于一个数x，如果x在[1, n]之间，将nums[x - 1]置为负数，符号表示被标记
        for (int i = 0; i < n; i++) {
            // 此时nums[i]可能为负数
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
