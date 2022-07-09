package LeetCode;

public class 将字符串翻转到单调递增 {
    public int minFlipsMonoIncr(String s) {
        int length = s.length();
        int[] a = new int[length + 1];
        int[] b = new int[length + 1];
        //统计前面子字符串"1"的个数的前缀和
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '1') {
                a[i + 1] = a[i] + 1;
            } else {
                a[i + 1] = a[i];
            }
        }
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                b[i] = b[i + 1] + 1;
            } else {
                b[i] = b[i + 1];
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= length; i++) {
            ans = Math.min(ans, a[i] + b[i]);
        }
        return ans;
    }
}
