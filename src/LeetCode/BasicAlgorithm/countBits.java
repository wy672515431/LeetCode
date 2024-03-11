package LeetCode.BasicAlgorithm;

public class countBits {
    public static int countOneBit(int x) {
        int count = 0;
        while (x != 0) {
            count++;
            // 总会将x的末尾的一置为0
            x = x & (x - 1);
        }
        return count;
    }
}
