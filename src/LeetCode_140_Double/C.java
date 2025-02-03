package LeetCode_140_Double;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class C {
    Map<Character, TreeSet<Integer>> map = new HashMap<>();
    List<Integer> res = new ArrayList<>();
    public int[] validSequence(String word1, String word2) {
        // 字符串x至多修改一个字符，使得它变成字符串y，则二者是相似的.
        int n = word1.length(), m = word2.length();
        for (int i = 0; i < n; i++) {
            char c = word1.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new TreeSet<>());
            }
            map.get(c).add(i);
        }
        if (dfs(word2, 0, false, -1)) {
            Collections.reverse(res);
            return res.stream().mapToInt(Integer::valueOf).toArray();
        }
        return new int[0];
    }

    private boolean dfs(String word2, int cur, boolean flag, int lastPos) {
        if (cur == word2.length()) {
            return true;
        }
        char ch = word2.charAt(cur);
        if (flag) {
            if (!map.containsKey(ch)) {
                return false;
            }
            // flag == true, 代表已经修改过一个字符了
            Integer nextPos = map.get(ch).higher(lastPos);
            if (nextPos == null) {
                return false;
            }
            if (dfs(word2, cur + 1, false, nextPos)) {
                res.add(nextPos);
                return true;
            }
            return false;
        } else {
            if (!map.containsKey(ch)) {
                if (dfs(word2, cur + 1, true, lastPos + 1)) {
                    res.add(lastPos + 1);
                    return true;
                }
                return false;
            }
            // 不更换
            Integer nextPos = map.get(ch).higher(lastPos);
            if (nextPos == null || nextPos > lastPos + 1) {
                if (dfs(word2, cur + 1, true, lastPos + 1)) {
                    res.add(lastPos + 1);
                    return true;
                }
            }
            if (nextPos != null) {
                if (dfs(word2, cur + 1, flag, nextPos)) {
                    res.add(nextPos);
                    return true;
                }
            }
            if (dfs(word2, cur + 1, true, lastPos + 1)) {
                res.add(lastPos + 1);
                return true;
            }
            // 更换
            return false;
        }
    }
}
