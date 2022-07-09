package LeetCode_298;

public class B {
    //最小的集合长度
    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        if (k == 0) {
            if (num % 10 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        //我们只需看num的最后一位
        for (int i = 1; ; i++) {
            if (num - k * i < 0) {
                break;
            }
            if ((num - k * i) % 10 == 0) {
                return i;
            }
        }
        return -1;
    }
}
