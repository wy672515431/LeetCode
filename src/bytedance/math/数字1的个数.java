package bytedance.math;

public class 数字1的个数 {
    public static void main(String[] args) {
        数字1的个数 main = new 数字1的个数();
        System.out.println(main.digitOneInNumber(1234));
    }
    int[] tables = new int[9];
    int[] preSum = new int[9];
    public int digitOneInNumber(int num) {
        initTables();
        String str = String.valueOf(num);
        return solve(str);
    }

    private int solve(String num) {
        if (num.length() == 1) {
            return "0".equals(num) ? 0 : tables[0];
        }
        int digit = num.charAt(0) - '0';
        if (digit == 0) {
            // 034
            return solve(num.substring(1));
        }
        if (digit == 1) {
            // 1234
            return digit * preSum[num.length() - 2] + Integer.parseInt(num.substring(1)) + 1 + solve(num.substring(1));
        } else {
            // 2345
            // 0 ~ 999
            // 1000 ~ 1999
            return digit * preSum[num.length() - 2] + (int) Math.pow(10, num.length() - 1) + solve(num.substring(1));
        }
    }

    private void initTables() {
        tables[0] = 1;
        int sum = tables[0];
        for (int i = 1; i < 9; i++) {
            tables[i] = sum * 9 + (int) Math.pow(10, i);
            sum += tables[i];
        }
        preSum[0] = tables[0];
        for (int i = 1; i < 9; i++) {
            preSum[i] = preSum[i - 1] + tables[i];
        }
    }

}
