package bytedance;

public class 放置盒子 {
    // 每一层可以放置盒子的数量为 (1 + 2 + ... + i)
    public int minimumBoxes(int n) {
        // 我们先看看可以放多少层
        int curLevel = 1;
        int sum = 1;
        while (n > sum) {
            // 减去前一层
            n -= sum;
            curLevel++;
            // 当前层最多可以放多少个
            sum += curLevel;
        }
        // 当前剩余的盒子数目为 n 个，且再curLevel层
        // 我们可以补充接地的最多盒子数为curLevel个
        // 每次多放一个接触地面的盒子，对总盒子数的贡献是i
        sum = 1;
        int groundBox = 1;
        while (n > sum) {
            n -= sum;
            groundBox++;
            sum++;
        }
        return curLevel * (curLevel - 1) / 2 + groundBox;
    }
}
