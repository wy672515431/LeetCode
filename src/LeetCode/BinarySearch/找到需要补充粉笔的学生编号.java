package LeetCode.BinarySearch;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 找到需要补充粉笔的学生编号 {
    public int chalkReplacer(int[] chalk, int k) {
        //求最后一轮剩余的粉笔数,值可能为0;
        long chalkSum = 0;
        for (int num : chalk) {
            chalkSum += num;
        }
        long chalksForLastRound = k % chalkSum;
        int sum = 0;
        for (int i = 0; i < chalk.length; i++) {
            sum += chalk[i];
            if (sum > chalksForLastRound)  {
                return i;
            }
        }
        return -1;
    }
}
