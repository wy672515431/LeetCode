package LeetCode_408;

public class B {
    public static void main(String[] args) {
        System.out.println(new B().nonSpecialCount(11, 12));
    }

    public int nonSpecialCount(int l, int r) {
        return r - l + 1 - getSpecialCount(l, r);
    }

    private int getSpecialCount(int l, int r) {
        // 找到平方大于等于l的第一个数
        int low = (int) ((int) Math.sqrt(l) * (int) Math.sqrt(l) == l ? Math.sqrt(l) : Math.sqrt(l) + 1);
        int high = (int) Math.sqrt(r);
        int ans = 0;
        for (int i = low; i <= high; i++) {
            if (i == 1) {
                continue;
            }
            if (i == 2 || i == 3) {
                ans++;
                continue;
            }
            boolean ok = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans++;
            }
        }
        return ans;
    }
}
