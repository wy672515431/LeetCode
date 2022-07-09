package LeetCode;

import java.util.*;

public class 词典中最长的单词 {
    public String longestWord(String[] words) {
        String ans = "";
        boolean isCheck = true;
        HashMap<Integer, String> mMap = new HashMap();
        for (int i = 0; i < words.length; i++) {
            mMap.put(i, words[i]);
        }
        for (int i = words.length - 1; i >= 0; i--) {
            for (int j = 0; j < words[i].length() - 1; j++) {
                String subStr = words[i].substring(0, j + 1);
                //如果包含
                if (!mMap.containsValue(subStr)) {
                    isCheck = false;
                }
            }
            if (isCheck) {
                if(words[i].length() > ans.length() || (words[i].length() == ans.length() && words[i].compareTo(ans) < 0)) {
                    ans = words[i];
                }
            }
            isCheck = true;
        }
        return ans;

    }


    public String longestWord1(String[] words) {
        Arrays.sort(words, (a, b) ->  {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            } else {
                return b.compareTo(a);
            }
        });
        String longest = "";
        Set<String> set = new HashSet<String>();
        set.add("");
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (set.contains(word.substring(0, word.length() - 1))) {
                set.add(word);
                longest = word;
            }
        }
        return longest;
    }
}
