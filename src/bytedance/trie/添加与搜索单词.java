package bytedance.trie;

import java.util.HashMap;
import java.util.Map;

public class 添加与搜索单词 {

}

class WordDictionary {
    private final Trie trie;
    public WordDictionary() {
        this.trie = new Trie();
    }

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return dfs(trie.root, word, 0);
    }

    private boolean dfs(Trie.TrieNode root, String word, int index) {
        if (index == word.length()) {
            return root.isEnd;
        }
        char ch = word.charAt(index);
        if (ch == '.') {
            // 代表它可能是任意节点
            for (Trie.TrieNode son : root.sons.values()) {
                if (dfs(son, word, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            if (!root.sons.containsKey(ch)) {
                return false;
            }
            return dfs(root.sons.get(ch), word, index + 1);
        }
    }

    static class Trie {
        TrieNode root;

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

        static class TrieNode {
            boolean isEnd;
            Map<Character, TrieNode> sons;

            TrieNode() {
                sons = new HashMap<>();
            }
        }
    }
}
