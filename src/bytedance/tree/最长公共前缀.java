package bytedance.tree;

import java.util.HashMap;
import java.util.Map;

public class 最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        Trie trie = new Trie();
        for (String str : strs) {
            trie.insert(str);
        }
        StringBuilder sb = new StringBuilder();
        TrieNode root = trie.root;
        // 子节点必须为1，同时为end之后不应该继续遍历下去 ["ab", "abc"]
        while (root.son.size() == 1 && !root.isEnd) {
            for (Character ch : root.son.keySet()) {
                sb.append(ch);
                root = root.son.get(ch);
            }
        }
        return sb.toString();
    }

    class TrieNode {
        Map<Character, TrieNode> son;
        boolean isEnd;
        TrieNode() {
            son = new HashMap<>();
        }
    }

    class Trie {
        TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode tmp = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                tmp.son.computeIfAbsent(ch, k -> new TrieNode());
                tmp = tmp.son.get(ch);
            }
            tmp.isEnd = true;
        }
    }

}
