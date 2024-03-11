package LeetCode_307;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class B {
    public static void main(String[] args) {
        String s = "sayhelloworld";
        String[] dictionary = {"hello", "world"};
        B b = new B();
        b.minExtraChar(s, dictionary);
    }
    public int minExtraChar(String s, String[] dictionary) {
        int len = s.length();
        int[] dp = new int[len + 1];
        Set<String> set = Arrays.stream(dictionary).collect(Collectors.toSet());
        for (int i = 0; i < len; i++) {
            dp[i + 1] = i + 1;
        }
        dp[1] = set.contains(s.substring(0, 1)) ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
             String substr = s.substring(0, i + 1);
             for (String word : dictionary) {
                 int index = substr.lastIndexOf(word);
                 if (index == -1) {
                     continue;
                 }
                 // 新加入的字符与前面构成了字符串
                 if (index + word.length() - 1 == i) {
                     dp[i + 1] = Math.min(dp[i + 1], dp[i - word.length() + 1]);
                 } else {
                     dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
                 }
             }
        }
        return dp[len];
    }
}
