package bytedance.字符串;

public class 找出字符串中第一个匹配项的下标 {
    public static void main(String[] args) {
        找出字符串中第一个匹配项的下标 main = new 找出字符串中第一个匹配项的下标();
        System.out.println(main.strStr("bccabcaac", "bccabcaac"));
    }

    public int strStr(String haystack, String needle) {
        // kmp算法
        // next数组: next[i]代表如果needle中第i个字符匹配失败了，重新匹配时，可以在needle中跳过多少个字符
        int n = haystack.length();
        int m = needle.length();

        int[] next = new int[m];
        // build next: next[0] = 0
        for (int i = 1, j = 0; i < m; i++) {
            // 如果不相同
            while (j > 0 && needle.charAt(j) != needle.charAt(i)) {
                // j没匹配上，能跳过多少字符呢？next[j - 1]
                j = next[j - 1];
            }
            if (needle.charAt(j) == needle.charAt(i)) {
                j++;
            }
            next[i] = j;
        }

        for (int i = 0; i < m; i++) {
            System.out.println(next[i]);
        }


        // 匹配
        for (int i = 0, j = 0; i < n; i++) {
            // 如果不成功
            while (j > 0 && needle.charAt(j) != haystack.charAt(i)) {
                j = next[j - 1];
            }
            // 如果相同，前进
            if (needle.charAt(j) == haystack.charAt(i)) {
                j++;
            }
            // 匹配完成
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
