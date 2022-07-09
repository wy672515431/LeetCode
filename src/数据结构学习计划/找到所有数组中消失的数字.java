package 数据结构学习计划;

import java.util.ArrayList;
import java.util.List;

public class 找到所有数组中消失的数字 {
    /**
     * nums[i] >= 1 && nums[i] <= n
     * 找出所有在 [1, n] 范围内但没有出现在 nums 中的数字
     * 映射
     * nums[i] = i
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                int tem = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tem;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
