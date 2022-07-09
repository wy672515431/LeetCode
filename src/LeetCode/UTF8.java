package LeetCode;

public class UTF8 {
    public static boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length; i++) {
            String binaryStr = Integer.toBinaryString(data[i] & 0xff);
            if (binaryStr.length() < 8) {
                continue;
            } else if (binaryStr.startsWith("110")) {
                if (i + 1 >= data.length) {
                    return false;
                } else {
                    if (!Integer.toBinaryString(data[i + 1] & 0xff).startsWith("10") || Integer.toBinaryString(data[i + 1] & 0xff).length() < 8)
                        return false;
                    else
                        i = i + 1;
                }
            } else if (binaryStr.startsWith("1110")) {
                if (i + 2 >= data.length) {
                    return false;
                } else {
                    for (int j = 1; j <= 2; j++) {
                        if (!Integer.toBinaryString(data[i + j] & 0xff).startsWith("10") || Integer.toBinaryString(data[i + j] & 0xff).length() < 8)
                            return false;
                    }
                    i = i + 2;
                }
            } else if (binaryStr.startsWith("11110")) {
                if (i + 3 >= data.length) {
                    return false;
                } else {
                    for (int j = 1; j <= 3; j++) {
                        if (!Integer.toBinaryString(data[i + j] & 0xff).startsWith("10") || Integer.toBinaryString(data[i + j] & 0xff).length() < 8)
                            return false;
                    }
                    i = i + 3;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(130 & 0xff));
    }
}
