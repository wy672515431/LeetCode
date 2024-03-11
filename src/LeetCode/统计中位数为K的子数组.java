package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class 统计中位数为K的子数组 {
    Map<Integer, Integer> map = new HashMap<>();

    public int countSubarrays(int[] nums, int k) {
        int kIndex = 0;
        int length = nums.length;
        int ans = 1;
        for (int i = 0; i < length; i++) {
            if (nums[i] == k) {
                kIndex = i;
                break;
            }
        }
        Node[] nodes = new Node[kIndex + 1];
        for (int i = 0; i <= kIndex; i++) {
            nodes[i] = new Node();
        }
        // calc [0~kIndex)
        for (int i = kIndex - 1; i >= 0; i--) {
            Node last = nodes[i + 1];
            nodes[i].less += last.less;
            nodes[i].greater += last.greater;
            if (nums[i] < k) {
                nodes[i].less += 1;
            } else {
                nodes[i].greater += 1;
            }
            put(nodes[i]);
            if (check(nodes[i])) {
                ans++;
            }
        }
        // calc [kIndex + 1, length)
        Node pre = new Node();
        for (int i = kIndex + 1; i < length; i++) {
            Node node = new Node();
            node.less += pre.less;
            node.greater += pre.greater;
            if (nums[i] < k) {
                node.less += 1;
            } else {
                node.greater += 1;
            }
            ans += get(node);
            if (check(node)) {
                ans++;
            }
            pre = node;
        }
        return ans;
    }

    public boolean check(Node node) {
        int len = node.less + node.greater + 1;
        if (isOdd(len)) {
            return node.less == node.greater;
        } else {
            return node.less == node.greater - 1;
        }
    }

    public void put(Node node) {
        int diff = node.less - node.greater;
        map.put(diff, map.getOrDefault(diff, 0) + 1);
    }

    public int get(Node node) {
        int diff = node.less - node.greater;
        // 这个是奇数的情况
        // 还有对应偶数的情况
        return map.getOrDefault(-diff, 0) + map.getOrDefault(-diff - 1, 0);
    }

    public boolean isOdd(int val) {
        return (val & 1) == 1;
    }

    class Node {
        int less;
        int greater;

        public Node(int less, int greater) {
            this.less = less;
            this.greater = greater;
        }

        public Node() {
            this.less = 0;
            this.greater = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return less == node.less && greater == node.greater;
        }

        @Override
        public int hashCode() {
            return Objects.hash(less, greater);
        }
    }

    public static void main(String[] args) {
        统计中位数为K的子数组 test = new 统计中位数为K的子数组();
        int[] nums = {5, 19, 11, 15, 13, 16, 4, 6, 2, 7, 10, 8, 18, 20, 1, 3, 17, 9, 12, 14};
        test.countSubarrays(nums, 6);
    }
}
