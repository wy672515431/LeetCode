package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 串联所有单词的子串 {
    /**
     * 时间复杂度O(s.length * words[0].length)
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        //窗口的大小
        int size = words[0].length() * words.length;
        //需要判断单词是否在words中，且判断单词是否在该窗口出现
        HashMap<String, Integer> mMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer val = mMap.putIfAbsent(words[i], 1);
            if (val != null) {
                mMap.put(words[i], val + 1);
            }
        }
        int start;
        for (start = 0; start + size - 1 < s.length(); start++) {
            HashMap<String, Integer> map = new HashMap<>(mMap);
            boolean ok = true;
            for (int j = 0; j < words.length; j++) {
                String substr = s.substring(start + words[0].length() * j,
                        start + words[0].length() * (j + 1));
                if (map.get(substr) == null || map.get(substr) <= 0) {
                    ok = false;
                    break;
                } else {
                    map.computeIfPresent(substr, (str, count) -> count - 1);
                }
            }
            if (ok)  {
                ans.add(start);
            }
        }
        return ans;
    }

    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int m = words.length, n = words[0].length(), ls = s.length();
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> differ = new HashMap<String, Integer>();
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            //统计窗口和数组之间的频次差
            //将窗口右移是，左边会减少一个单词，右边会增加一个单词，对differ做更新。
            //若出现differ中值不为0的键的数量为0，则表示这个窗口中的单词频次和words中单词频次相同
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            //每次移动以一个单词长度来移动
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {
                    //右侧单词
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    //左侧单词
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }

}
