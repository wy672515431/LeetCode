package LeetCode_415;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class C {
    public static void main(String[] args) {
        C c = new C();
        String[] words = {"abc", "aaaaa", "bcdef"};
        String target = "aabcdabc";
        System.out.println(c.minValidStrings(words, target));
    }

    public int minValidStrings(String[] words, String target) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        int n = target.length();
        // dp[i]表示target 的前i个字符需要的最少字符串数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] >= Integer.MAX_VALUE / 2) {
                break;
            }
            if (end == n) {
                break;
            }
            if (i > end) {
                break;
            }
            int j;
            for (j = end; j < n; j++) {
                String str = target.substring(i, j + 1);
                if (trie.startsWith(str)) {
                    dp[j + 1] = Math.min(dp[j + 1], dp[i] + 1);
                } else {
                    end = j;
                    break;
                }
            }
            if (j == n) {
                end = n;
            }
        }
        return dp[n] >= Integer.MAX_VALUE / 2 ? -1 : dp[n];
    }

    static class Trie {
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
}
