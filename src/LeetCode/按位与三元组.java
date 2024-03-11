package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class 按位与三元组 {

    public int countTriplets(int[] nums) {
        int[] cnt = new int[1 << 16];
        for (int x : nums) {
            for (int y : nums) {
                ++cnt[x & y];
            }
        }
        int ans = 0;
        for (int x : nums) {
            // 反转位数
            x = x ^ 0xffff;
            // nums[k]异或运算得到一个数，显然该数&nums[k]为0。
            // 满足要求的二元组的二进制表示中包含的1必须是该数的子集.
            // 100111 -> 000111
            // sub = (sub - 1) & x可以为子集
            for (int sub = x; sub != 0; sub = (sub - 1) & x) {
                ans += cnt[sub];
            }
            ans += cnt[0];
        }
        return ans;
    }
}
