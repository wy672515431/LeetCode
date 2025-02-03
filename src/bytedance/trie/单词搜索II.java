package bytedance.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 单词搜索II {
    int n, m;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;
    Trie trie;
    Set<String> res = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    public List<String> findWords(char[][] board, String[] words) {
        trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        n = board.length;
        m = board[0].length;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                solve(board, trie.root, i, j);
            }
        }
        return new ArrayList<>(res);
    }

    private void solve(char[][] board, Trie.TrieNode root, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        sb.append(board[x][y]);

        // 这样减少了一次search调用

        if (!root.sons.containsKey(board[x][y])) {
            sb.deleteCharAt(sb.length() - 1);
            visited[x][y] = false;
            return;
        }

        root = root.sons.get(board[x][y]);

        if (root.isEnd) {
            res.add(sb.toString());
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            solve(board, root, nx, ny);
        }
        sb.deleteCharAt(sb.length() - 1);
        visited[x][y] = false;
    }

    static class Trie {

        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                node = node.sons.computeIfAbsent(ch, k -> new TrieNode());
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!node.sons.containsKey(ch)) {
                    return false;
                }
                node = node.sons.get(ch);
            }
            return node.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (!node.sons.containsKey(ch)) {
                    return false;
                }
                node = node.sons.get(ch);
            }
            return true;
        }


        static class TrieNode {
            boolean isEnd;
            Map<Character, TrieNode> sons;

            public TrieNode() {
                isEnd = false;
                sons = new HashMap<>();
            }
        }
    }
}
