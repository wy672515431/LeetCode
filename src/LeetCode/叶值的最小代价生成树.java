package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class 叶值的最小代价生成树 {
    Map<Integer, Integer> map = new HashMap<>();

    public int mctFromLeafValues(int[] arr) {
        return countVal(arr, 0, arr.length - 1);
    }

    public int countVal(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        if (map.get(start * 41 + end) != null) {
            return map.get(start * 41 + end);
        }
        int countVal = Integer.MAX_VALUE;
        // 拆分为左数组和右数组
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            int leftVal = countVal(arr, start, i);
            int rightVal = countVal(arr, i + 1, end);
            for (int j = start; j <= i; j++) {
                leftMax = Math.max(leftMax, arr[j]);
            }
            for (int j = i + 1; j <= end; j++) {
                rightMax = Math.max(rightMax, arr[j]);
            }
            countVal = Math.min(leftVal + rightVal + leftMax * rightMax, countVal);
        }
        map.put(start * 41 + end, countVal);
        return countVal;
    }

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int len = price.length;
        int ans = 0;
        int low = 0;
        int high = price[len - 1] - price[0];
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (check(price, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public boolean check(int[] price, int k, int diff) {
        int size = 1;
        int lastCandy = price[0];
        for (int i = 1; i < price.length; i++) {
            if (price[i] - lastCandy >= diff) {
                lastCandy = price[i];
                size++;
            }
        }
        return size >= k;
    }


    public static void main(String[] args) {
        叶值的最小代价生成树 a = new 叶值的最小代价生成树();
        int[] price = {13, 5, 1, 8, 21, 2};
        System.out.println(a.maximumTastiness(price, 3));
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode i = dummy.next;
        ListNode j = i;
        for (; i != null; i = i.next) {
            int sum = 0;
            for (j = i; j != null; j = j.next) {
                sum += j.val;
                // 删除
                if (sum == 0) {
                    pre.next = j.next;
                    i = pre;
                    break;
                }
            }
            if (j == null) {
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}
