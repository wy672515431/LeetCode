package LeetCode;

import java.util.Arrays;

public class 最长特殊序列 {
    public int findLUSlength(String[] strs) {
        //从大到小排序
        Arrays.sort(strs, (a,b) -> b.length() - a.length());
        for (int i = 0; i < strs.length; i++) {
            boolean ok = true;
            for (int j = 0; j < strs.length; j++) {
                if (j == i) {
                    continue;
                } else if (strs[i].length() > strs[j].length()) {
                    break;
                } else {
                    if (isSubsequence(strs[i], strs[j])) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                return strs[i].length();
            }
        }
        return -1;
    }

    /**
     * 判断str是否是anotherStr的子序列
     * @param str
     * @param anotherStr
     * @return
     */
    public boolean isSubsequence(String str, String anotherStr) {
        int i = 0, j = 0;
        while (i < str.length() && j < anotherStr.length()) {
            if (str.charAt(i) == anotherStr.charAt(j)) {
                i++;
            }
            j++;
        }
        if (i == str.length()) {
            return true;
        }
        return false;
    }
}
