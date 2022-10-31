package LeetCode_304;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class A {
    public static int minimumOperations(int[] nums) {
            int ans = 0;
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    queue.offer(nums[i]);
                }
            }
            while (!queue.isEmpty()) {
                int minNum = queue.peek();
                ArrayList<Integer> list = new ArrayList<>();
                while (!queue.isEmpty()) {
                    int curNum = queue.poll();
                    if (curNum - minNum > 0) {
                        list.add(curNum - minNum);
                    }
                }
                for (Integer integer : list) {
                    queue.offer(integer);
                }
                ans++;
            }
            return ans;
    }

    public static void main(String[] args) {
        int[] test = {1,5,0,3,5};
        minimumOperations(test);
    }
}
