package LeetCode_315;

public class C {
    public boolean sumOfNumberAndReverse(int num) {
        for (int i = 0; i <= num; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            int reverse = Integer.parseInt(sb.reverse().toString());
            if (i + reverse == num) {
                return true;
            }
        }
        return false;
    }
}
