package LeetCode_298;

public class C {
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 31) - 1);
    }
    public int longestSubsequence(String s, int k) {
        //选取最长的序列等价于删除最少的digit
        //删除的digit应该全为1，我们从右向左遍历，保留最右边的1，删除左边的1
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                ans++;
            }
        }
        int val = 0;
        //防止超整型
        for (int i = s.length() - 1; i >= Math.max(0, s.length() - 31); i--) {
            if (s.charAt(i) == '1') {
                val = val + (1 << (s.length() - 1- i));
                if (val <= k) {
                    ans++;
                } else {
                    break;
                }
            }
        }
        return ans;
    }
}
