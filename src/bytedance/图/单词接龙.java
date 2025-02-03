package bytedance.图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 单词接龙 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<List<Integer>> edges = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        list.addAll(wordList);
        int n = list.size();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (canChange(list.get(i), list.get(j))) {
                    edges.get(i).add(j);
                    edges.get(j).add(i);
                }
            }
        }
        int ans = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (visited[cur]) {
                    continue;
                }
                visited[cur] = true;
                if (list.get(cur).equals(endWord)) {
                    return ans;
                }
                for (int next : edges.get(cur)) {
                    if (!visited[next]) {
                        queue.offer(next);
                    }
                }
            }
        }
        return 0;
    }

    private boolean canChange(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }
}
