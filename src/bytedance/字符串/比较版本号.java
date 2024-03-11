package bytedance.字符串;

public class 比较版本号 {
    public int compareVersion(String version1, String version2) {
        String[] version1s = version1.split("\\.");
        String[] version2s = version2.split("\\.");
        int i = 0, j = 0;
        while (i < version1s.length || j < version2s.length) {
            String str1 = i >= version1s.length ? "0" : version1s[i];
            String str2 = j >= version2s.length ? "0" : version2s[j];
            int num1 = Integer.parseInt(str1);
            int num2 = Integer.parseInt(str2);
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
            i++;
            j++;
        }
        return 0;
    }
}
