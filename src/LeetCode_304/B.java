package LeetCode_304;

import java.util.Arrays;

public class B {
    public int maximumGroups(int[] grades) {
        //
        int ans = 1;
        Arrays.sort(grades);
        int preSum = grades[0];
        int preLen = 1;
        int curSum = 0;
        int curLen = 0;
        for (int i = 1; i < grades.length; i++) {
            curSum += grades[i];
            curLen++;
            if (curSum > preSum && curLen > preLen) {
                preSum = curSum;
                preLen = curLen;
                curSum = 0;
                curLen = 0;
                ans++;
            }
        }
        return ans;
    }
}
