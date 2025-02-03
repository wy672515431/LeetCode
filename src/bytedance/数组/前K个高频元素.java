package bytedance.数组;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 前K个高频元素 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        // 当然我们也可以利用快排partition的思想，找到前K大的元素
        PriorityQueue<Node> pq = new PriorityQueue<>(k, (a, b) -> b.freq - a.freq);
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().val;
        }
        return res;
    }

    int[] ans;
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        ans = new int[k];
        Node[] nodes = new Node[freqMap.size()];
        int cnt = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            nodes[cnt++] = new Node(entry.getKey(), entry.getValue());
        }
        partition(nodes, 0, nodes.length - 1, 0, k);
        return ans;
    }

    private void partition(Node[] nodes, int l, int r, int index, int k) {
        Node pivot = nodes[l];
        int i = l, j = r;
        while (i < j) {
            // 左边都是大于等于pivot的
            while (i < j && nodes[j].freq < pivot.freq) {
                j--;
            }
            if (i < j) {
                nodes[i] = nodes[j];
                i++;
            }
            while (i < j && nodes[i].freq >= pivot.freq) {
                i++;
            }
            if (i < j) {
                nodes[j] = nodes[i];
                j--;
            }
        }
        nodes[i] = pivot;
        // 在左侧数组里面
        if (i - l + 1 > k) {
            partition(nodes, l, i - 1, index, k);
        } else {
            // 左侧全部
            for (int x = l; x <= i; x++) {
                ans[index++] = nodes[x].val;
            }
            // 右侧还剩 k - (i - l + 1)个
            if (k > i - l + 1) {
                partition(nodes, i + 1, r, index, k - (i - l + 1));
            }
        }
    }

    static class Node {
        int val;
        int freq;
        Node(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }
}
