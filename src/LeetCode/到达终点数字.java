package LeetCode;

/**
 * LeetCode 754
 */
public class 到达终点数字 {
    /**
     * 分类讨论，第一种情况，不回头走n步能够恰好到达终点，答案为n
     * 第二种情况，超越终点，且距离为偶数。我们只需反向走一步即可
     * 第三种情况，超越终点，且距离为奇数，多走一步，如果变为偶数，则转换为第二种情况
     * 第四种情况，超越终点，距离为奇数，多走一步还是奇数，再走一步，必定是偶数
     * @param target
     * @return
     */
    public int reachNumber(int target) {
        target = Math.abs(target);
        int ans = 0, dis = 0;
        while (dis < target || (dis - target) % 2 == 1) {
            ans++;
            dis += ans;
        }
        return ans;
    }
}
