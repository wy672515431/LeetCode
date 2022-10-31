package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class 链表组件 {
    public int numComponents(ListNode head, int[] nums) {
        HashSet<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));
        int ans = 0;
        int count = 0;
        while (head != null) {
            if (set.contains(head.val)) {
                count++;
            } else {
                if (count != 0) {
                    ans++;
                }
                count = 0;
            }
            head = head.next;
        }
        if (count != 0) {
            ans++;
        }
        return ans;
    }
}
