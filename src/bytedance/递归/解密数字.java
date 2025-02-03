package bytedance.递归;

public class 解密数字 {
    int ans = 0;
    public int crackNumber(int ciphertext) {
        solve(String.valueOf(ciphertext), 0);
        return ans;
    }

    private void solve(String ciphertext, int cur) {
        if (cur == ciphertext.length()) {
            ans++;
            return;
        }
        int sum = 0;
        for (int i = cur; i < Math.min(cur + 2, ciphertext.length()); i++) {
            // 避免前缀0
            if (i != cur && ciphertext.charAt(cur) == '0') {
                return;
            }
            sum = sum * 10 + (ciphertext.charAt(i) - '0');
            if (sum >= 0 && sum <= 25) {
                solve(ciphertext, i + 1);
            }
        }
    }
}
