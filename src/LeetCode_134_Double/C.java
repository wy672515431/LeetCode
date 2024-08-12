package LeetCode_134_Double;

public class C {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        // 环中k个相邻的颜色不能相同
        int n = colors.length;
        int ans = 0;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            int j = 0;
            if (flag) {
                if (colors[(i + k - 2) % n] == colors[(i + k - 1) % n]) {
                    ans++;
                    continue;
                } else {
                    i = i + k - 2;
                    continue;
                }
            }
            for (; j < k - 1; j++) {
                if (colors[(i + j) % n] == colors[(i + j + 1) % n]) {
                    break;
                }
            }
            if (j == k - 1) {
                ans++;
                flag = true;
            } else {
                i += j;
            }
        }
        return ans;
    }
}
