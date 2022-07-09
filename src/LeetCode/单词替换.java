package LeetCode;

import java.util.List;

public class 单词替换 {
    //字典树
    static final int maxn = 1000010;
    //字典树，每个节点最多有26个子节点
    int[][] tire = new int[maxn][26];
    //表明该节点是不是最终节点
    int[] cnt = new int[maxn];
    //节点编号，从1开始
    int id = 1;
    public String replaceWords(List<String> dictionary, String sentence) {
        for (String str : dictionary) {
            insert(str);
        }
        StringBuilder sb = new StringBuilder();
        String[] successors = sentence.split(" ");
        for (int i = 0; i < successors.length; i++) {
            String root = find(successors[i]);
            if (root.equals("")) {
                sb.append(successors[i]);
            } else {
                sb.append(root);
            }
            if (i != successors.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * 向字典树中插入字符串s
     * @param s
     */
    private void insert(String s) {
        //从根节点0开始
        int root = 0;
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            if (tire[root][ch] == 0) {
                tire[root][ch] = id++;
            }
            //到下一个节点
            root = tire[root][ch];
        }
        //结尾节点加1，表示是结尾节点.
        cnt[root]++;
    }

    /**
     * 找到s在字典树中的最短前缀
     * @param s
     * @return
     */
    private String find(String s) {
        StringBuilder sb = new StringBuilder();
        //从根节点root开始
        int root = 0;
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            if (tire[root][ch] != 0 ) {
                char ch1 = (char)('a' + ch);
                sb.append(ch1);
                root = tire[root][ch];
            } else {
                return "";
            }
            //结束节点
            if (cnt[root] != 0) {
                return sb.toString();
            }
        }
        return "";
    }
}
