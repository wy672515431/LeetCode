package LeetCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class 最小覆盖子串 {
    /**
     * 找出s中覆盖t的最小子串
     * t中的重复字符，寻找的子字符串中该字符数量必须不少于t中该字符数量。
     * 滑动窗口
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        if (slen < tlen) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        // init
        for (int i = 0; i < tlen; i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int start = 0;
        int end = tlen - 1;
        StringBuilder sb = new StringBuilder();
        String ans = "";
        for (int i = start; i <= end; i++) {
            char ch = s.charAt(i);
            sb.append(ch);
            if (map.get(ch) != null) {
                map.put(ch, map.get(ch) - 1);
            }
        }
        while (end < slen) {
            if (isCover(map)) {
                ans = (sb.toString().length() < ans.length() || ans.equals("")) ? sb.toString() : ans;
                if (start < slen) {
                    char ch = s.charAt(start);
                    if (map.get(ch) != null) {
                        map.put(ch, map.get(ch) + 1);
                    }
                }
                start++;
                sb.deleteCharAt(0);
            } else {
                end++;
                if (end < slen) {
                    char ch = s.charAt(end);
                    if (map.get(ch) != null) {
                        map.put(ch, map.get(ch) - 1);
                    }
                    sb.append(ch);
                }
            }
        }
        return ans;
    }

    public static boolean isCover(HashMap<Character, Integer> map) {
        for (int val : map.values()) {
            if (val > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        minWindow(s, t);
    }

    /* -------------- 滑动窗口 */
    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow1(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        // 滑动窗口的左右边界，每一次只能改变一个边界
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            // 如果能够覆盖，就减少左边界直到不能，这样能够获得最小长度。
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}
