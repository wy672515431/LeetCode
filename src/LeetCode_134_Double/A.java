package LeetCode_134_Double;

public class A {
    public int numberOfAlternatingGroups(int[] colors) {
        int ans = 0;
        int n = colors.length;
        for (int i = 0; i < n - 2; i++) {
            if (colors[i + 1] != colors[i] && colors[i + 1] != colors[i + 2]) {
                ans++;
            }
        }
        ans += (colors[n - 1] != colors[n - 2] && colors[n - 1] != colors[0]) ? 1 : 0;
        ans += (colors[0] != colors[1] && colors[0] != colors[n - 1]) ? 1 : 0;
        return ans;
    }
}
