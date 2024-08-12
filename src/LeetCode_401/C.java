package LeetCode_401;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class C {
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        int sum = rewardValues[0];
        int ans = sum;
        for (int i = 1; i < n; i++) {

        }
        return ans;
    }
}
