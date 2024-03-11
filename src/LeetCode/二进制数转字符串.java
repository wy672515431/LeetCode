package LeetCode;

public class 二进制数转字符串 {

    /**
     *
     * @param num num ranges in (0, 1)
     * @return
     */
    public String printBin(double num) {
        StringBuilder sb = new StringBuilder("0.");
        int cnt = 0;
        while (true) {
            num = num * 2;
            int bit = (num < 1.0) ? 0 : 1;
            num -= bit;
            sb.append(bit);
            cnt++;
            if (cnt > 30 || num == 0.0) {
                break;
            }
        }
        if (num != 0) {
            return "ERROR";
        }
        return sb.toString();
    }

}
