package bytedance.random;

import LeetCode.ListNode;

import java.util.Random;

public class 链表随机节点 {
}

class Solution {
    private ListNode head;
    private Random random;
    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        // 水塘抽样法
        // 第i个元素的值作为答案的概率 = 第i次随机数取值为0 * (第 i + 1次不为0) * （第n次不为0）
        //                         = 1 / i * (i / (i + 1)) * (n - 1 / n) = 1 / n
        // 如果每次要选m个，则首先将头m个选中，后面的每个元素选中的概率为 m / i
        int cnt = 1;
        int ans = 0;
        for (ListNode temp = head; temp != null; temp = temp.next) {
            if (random.nextInt(cnt) == 0) { // 1 / i 的概率被抽中, (i - 1) / i 不被选中
                ans = temp.val;
            }
            cnt++;
        }
        return ans;
    }
}
