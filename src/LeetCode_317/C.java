package LeetCode_317;

import java.util.ArrayList;
import java.util.List;

public class C {
    public static long makeIntegerBeautiful(long n, int target) {
        long ans = 0;
        if (getSumOfEveryBit(n) <= target) {
            return ans;
        }
        List<Long> list = getKthSum(n);
        for (long num : list) {
            if (getSumOfEveryBit(n + num) <= target) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    public static int getSumOfEveryBit(long n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }

    public static List<Long> getKthSum(long n) {
        List<Long> list = new ArrayList<>();
        long sum = 0;
        long over = 1;
        while (n > 0) {
            sum = n % 10 * over + sum;
            over = over * 10;
            list.add(over - sum);
            n = n / 10;
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(makeIntegerBeautiful(6068060761L,  3));
    }
}
