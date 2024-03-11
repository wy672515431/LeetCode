package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class 有序矩阵中的第k个最小数字和 {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.sum));
        Set<Node> set = new HashSet<>();
        int cnt = 1;
        int[] initialIndexes = new int[m];
        int smallestSum = Arrays.stream(mat).mapToInt(o -> o[0]).sum();
        Node smallestNode = new Node(smallestSum, initialIndexes);
        queue.offer(smallestNode);
        set.add(smallestNode);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int sum = node.sum;
            int[] indexes = node.indexes;
            if (cnt == k) {
                return sum;
            }
            for (int i = 0; i < m; i++) {
                if (indexes[i] != n - 1) {
                    int newSum = sum - mat[i][indexes[i]] + mat[i][indexes[i] + 1];
                    int[] newIndexes = Arrays.copyOf(indexes, m);
                    newIndexes[i] = indexes[i] + 1;
                    Node newNode = new Node(newSum, newIndexes);
                    if (!set.contains(newNode)) {
                        queue.offer(newNode);
                        set.add(newNode);
                    }
                }
            }
            cnt++;
        }
        return Integer.MAX_VALUE;
    }

    class Node {
        int sum;
        int[] indexes;
        Node(int sum, int[] indexes) {
            this.sum = sum;
            this.indexes = indexes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return sum == node.sum && Arrays.equals(indexes, node.indexes);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(sum);
            result = 31 * result + Arrays.hashCode(indexes);
            return result;
        }
    }
}
