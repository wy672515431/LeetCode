package LeetCode_83_Double;

import java.util.HashSet;

public class D {
    public int shortestSequence(int[] rolls, int k) {
        HashSet<Integer> set = new HashSet<>();
        int ans = 1;
        for (int num : rolls) {
            set.add(num);
            if (set.size() == k) {
                ans++;
                set.clear();
            }
        }
        return ans;
    }
}
