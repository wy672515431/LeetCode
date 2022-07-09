package 数据结构学习计划;

public class 最短回文串 {
    /**
     * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。
     * 找到并返回可以用这种方式转换的最短回文串。
     * 显然我们需要找到字符串s的前缀中最长的回文串
     *
     * 可以采用字符串哈希
     * 我们将字符串看成一个base进制的数，它对应的十进制就是哈希值
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        int len = s.length();
        int base = 131;
        int mod = 1000000007;
        int left = 0;
        int right = 0;
        int mul = 1;
        int best = -1;
        for (int i = 0; i < len; i++) {
            left = (int)(((long)left * base + s.charAt(i)) % mod);
            right = (int)((right + (long)mul * s.charAt(i)) % mod);
            if (left == right) {
                best = i;
            }
            mul = (int)((long) mul * base % mod);
        }
        String add = (best == len - 1) ? "" : s.substring(best + 1);
        StringBuilder sb = new StringBuilder(add).reverse();
        return sb.append(s).toString();
    }

    /**
     * KMP
     * KMP的next数组存储了最长公共前后缀
     *
     * @param s
     * @return
     */
    public String shortestPalindrome1(String s) {
        String reverseStr = new StringBuilder(s).reverse().toString();
        String newStr = s + "#" + reverseStr;
        int[] next = new int[newStr.length()];
        next[0] = 0;
        int temp = 0;
        int i = 1;
        while (i < newStr.length()) {
            if (newStr.charAt(temp) == newStr.charAt(i)) {
                temp += 1;
                next[i] = temp;
                i += 1;
            } else if (temp != 0) {
                temp = next[temp - 1];
            } else {
                next[i] = 0;
                i += 1;
            }
        }
//        //主串中将要匹配的位置
//        int tar = 0;
//        //模式串中将要匹配的位置
//        int pos = 0;
//        String pattern = "123";
//        while (tar < s.length()) {
//            if (s.charAt(tar) == pattern.charAt(pos)) {
//                pos++;
//                tar++;
//            } else if (pos != 0) {
//                pos = next[pos - 1];
//            } else {
//                tar++;
//            }
//        }
        int len = next[newStr.length() - 1];
        String s1 = s.substring(len);
        StringBuilder ans = new StringBuilder(s1).reverse().append(s);
        return ans.toString();
    }

}
