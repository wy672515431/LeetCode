package bytedance.设计;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoCompleteSystem {
    Trie trie;
    StringBuilder sb = new StringBuilder();
    public AutoCompleteSystem(String[] sentences, int[] times) {
        this.trie = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String sentence = sb.toString();
            if (!trie.find(sentence)) {
                trie.insert(sentence, 1);
            }
            sb.setLength(0);
            return new ArrayList<>();
        }
        sb.append(c);
        return trie.search(sb.toString());
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode((char)0);
        }

        public void insert(String sentence, int times) {
            TrieNode cur = root;
            for (char ch : sentence.toCharArray()) {
                if (!cur.children.containsKey(ch)) {
                    cur.children.put(ch, new TrieNode(ch));
                }
                cur = cur.children.get(ch);
            }
            cur.isEnd = true;
            cur.times = times;
            cur.sentence = sentence;
        }

        public boolean find(String sentence) {
            TrieNode cur = root;
            for (char ch : sentence.toCharArray()) {
                if (!cur.children.containsKey(ch)) {
                    return false;
                }
                cur = cur.children.get(ch);
            }
            if (cur.isEnd) {
                cur.times++;
            }
            return cur.isEnd;
        }

        public List<String> search(String prefix) {
            List<TrieNode> res = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                if (!cur.children.containsKey(ch)) {
                    return new ArrayList<>();
                }
                cur = cur.children.get(ch);
            }
            dfs(cur, res);
            res.sort((o1, o2) -> {
                if (o1.times == o2.times) {
                    return o1.sentence.compareTo(o2.sentence);
                } else {
                    return o2.times - o1.times;
                }
            });
            return res.stream().map(o -> o.sentence).toList().subList(0, Math.min(3, res.size()));
        }

        private void dfs(TrieNode root, List<TrieNode> res) {
            if (root.isEnd) {
                res.add(root);
            }
            for (TrieNode child : root.children.values()) {
                dfs(child, res);
            }
        }
    }

    static class TrieNode {
        char ch;
        Map<Character, TrieNode> children;
        boolean isEnd;
        int times;
        String sentence;

        TrieNode(char ch) {
            this.ch = ch;
            this.children = new HashMap<>();
            this.isEnd = false;
            this.times = 0;
            this.sentence = "";
        }
    }
}
