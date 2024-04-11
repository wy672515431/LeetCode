package bytedance.tree;

import LeetCode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class 实现Trie {

}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            curNode = curNode.sons.computeIfAbsent(ch, k -> new TrieNode());
        }
        curNode.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!curNode.sons.containsKey(ch)) {
                return false;
            }
            curNode = curNode.sons.get(ch);
        }
        return curNode.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode curNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!curNode.sons.containsKey(ch)) {
                return false;
            }
            curNode = curNode.sons.get(ch);
        }
        return true;
    }

    static class TrieNode {
        boolean isEnd;
        Map<Character, TrieNode> sons;

        TrieNode() {
            sons = new HashMap<>();
        }
    }
}
