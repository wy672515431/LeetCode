package LeetCode_92_Double;

public class C {
    public static int bestClosingTime(String customers) {
        int minCost = Integer.MAX_VALUE;
        int ans = 0;
        int n = customers.length();
        int[] numY = new int[n + 1];
        int[] numN = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'N') {
                numN[i + 1] = numN[i] + 1;
            } else {
                numN[i + 1] = numN[i];
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (customers.charAt(i) == 'Y') {
                numY[i] = numY[i + 1] + 1;
            } else {
                numY[i] = numY[i + 1];
            }
        }

        for (int i = 0; i <= n; i++) {
            if (numN[i] + numY[i] < minCost) {
                minCost = numN[i] + numY[i];
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "YNYY";
        bestClosingTime(str);
    }
}
