package bytedance.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 串联所有单词的子串 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int m = words.length, n = words[0].length(), ls = s.length();
        // 滑动窗口按照单词进行滑动，不是按照字符
        // 共有n中划分方式
        for (int i = 0; i < n; i++) {
            // i + m * n - 1 > ls - 1 代表划分不了m个单词了
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> differ = new HashMap<>();
            // 初始状态s划分的m个单词
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
                // 排除第一次划分
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
