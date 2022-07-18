package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class 水塘抽样 {
    //设计如下算法
    //从链表头开始，遍历整个链表，对遍历到的第i个节点，随机选择区间[0,i)的整数,如果其等于0，则将答案置为
    //该节点值，否则答案不变
    //第i个节点被返回的概率 = 第i次选位0 * 第 i + 1次选为不为0
    // 1 / i * (1 - 1 / (i + 1)) * (1 - 1 / n) = 1 / n
    Random random;
    ListNode head;
    public 水塘抽样(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        int cnt = 1, ans = 0;
        for (ListNode node = head; node != null; node = node.next) {
            if (random.nextInt(cnt) == 0) {
                ans = head.val;
            }
            cnt++;
        }
        return ans;
    }
}
