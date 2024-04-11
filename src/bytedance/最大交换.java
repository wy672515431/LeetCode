package bytedance;

public class 最大交换 {
    /**
     * 最大交换
     * 1. 尽可能把左边的小数和右边比它最大的数交换
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        char[] sequence = String.valueOf(num).toCharArray();
        int n = sequence.length, left = -1, right = n - 1, rightMax = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int bit = sequence[i] - '0';
            if (bit < (sequence[rightMax] - '0')) {
                // 交换的序列
                left = i;
                right = rightMax;
            } else if (bit > (sequence[rightMax] -'0')){
                rightMax = i;
            }
        }
        if (left == -1) {
            return num;
        }
        char temp = sequence[right];
        sequence[right] = sequence[left];
        sequence[left] = temp;
        int ans = 0;
        for (char c : sequence) {
            ans = ans * 10 + (c - '0');
        }
        return ans;
    }
}
