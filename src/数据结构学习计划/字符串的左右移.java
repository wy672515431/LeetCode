package 数据结构学习计划;

public class 字符串的左右移 {
    public String stringShift(String s, int[][] shift) {
        StringBuilder originSb = new StringBuilder(s);
        StringBuilder nextSb = new StringBuilder();
        for (int i = 0; i < shift.length; i++) {
            nextSb.setLength(0);
            if (shift[i][0] == 0) {
                nextSb.append(originSb.substring(shift[i][1], originSb.length()));
                nextSb.append(originSb.substring(0, shift[i][1]));
            } else {
                nextSb.append(originSb.substring(originSb.length() - shift[i][1], originSb.length()));
                nextSb.append(originSb.substring(0, originSb.length() - shift[i][1]));
            }
            originSb.setLength(0);
            originSb.append(nextSb);
        }
        return nextSb.toString();
    }
}
