package bytedance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 数组组成最大数 {

    // Arrays.sort(input, (o1, o2) -> (o2 + o1).compareTo(o1 + o2))
    // 简洁的做法，每次比较时，比较两个字符串组合 o2 + o1 还是 o1 + o2大，如果o2 + o1大，则放在前面
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawInput = scanner.next();
        String[] array = rawInput.substring(1, rawInput.length() - 1).split(",");
        Map<Character, Integer> map = new HashMap<>();
        Node[] nodes = new Node[array.length];
        // 统计最大长度
        for (String s : array) {
            char first = s.charAt(0);
            if (!map.containsKey(first)) {
                map.put(first, s.length());
            } else {
                map.put(first, Math.max(s.length(), map.get(first)));
            }
        }
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            int maxLength = map.get(str.charAt(0));
            int weight = 0;
            for (int j = 0; j < maxLength; j++) {
                if (j < str.length()) {
                    weight += (int)Math.pow(10, maxLength - j - 1) * (str.charAt(j) - '0');
                } else {
                    weight += (int)Math.pow(10, maxLength - j - 1) * (str.charAt(0) - '0');
                }
            }
            nodes[i] = new Node(str, weight);
        }
        StringBuilder ans = new StringBuilder();
        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.val.charAt(0) != o2.val.charAt(0)) {
                return o2.val.compareTo(o1.val);
            } else {
                return o2.weight - o1.weight;
            }
        });
        for (Node node: nodes) {
            ans.append(node.val);
        }
        System.out.println(ans);
    }

    static class Node {
        String val;
        int weight;
        Node(String val, int weight) {
            this.val = val;
            this.weight = weight;
        }
    }
}
