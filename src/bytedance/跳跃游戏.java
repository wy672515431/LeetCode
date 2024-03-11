package bytedance;

public class 跳跃游戏 {
    /**
     * 起始处的下标为0
     * @param nums nums[i]代表在i处能够跳跃的最大长度
     * @return 返回是否能到达最后一个下标
     */
    public boolean canJump(int[] nums) {
        // 表示目前能够到达的最远距离
        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) {
                return false;
            }
            int jumpDist = i + nums[i];
            if (jumpDist > farthest) {
                farthest = jumpDist;
            }
        }
        return true;
    }
}
