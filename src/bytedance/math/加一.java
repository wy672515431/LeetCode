package bytedance.math;

public class 加一 {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        if (digits[0] == 0) {
            int[] res = new int[n + 1];
            res[0] = 1;
            for (int i = 1; i < n + 1; i++) {
                res[i] = digits[i - 1];
            }
            return res;
        }
        return digits;
    }
}
