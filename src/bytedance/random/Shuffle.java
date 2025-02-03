package bytedance.random;

import java.util.Random;

public class Shuffle {
    // 如何将一副牌随机打乱?
    // 最直接的做法是，1.随机从初始牌中抽取一张牌，放入到新牌中，并从初始牌中删除这张牌
    // 2.重复1直到初始牌为空

    // 时间复杂度过高
    // Fisher-Yates 洗牌算法

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        shuffle(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void shuffle(int[] nums) {
        Random random = new Random();
        // 第二个数没有被交换的概率 (n - 1) / n * (1 / n - 1)  = 1 / n
        // 依次类推
        for (int i = 0; i < nums.length; i++) {
            // 从[i, n)中随机选取一个数
            int j = i + random.nextInt(nums.length - i);
            // 将nums[i]与nums[j]交换
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
