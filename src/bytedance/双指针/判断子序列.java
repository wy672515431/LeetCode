package bytedance.双指针;

public class 判断子序列 {
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
