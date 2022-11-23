package LeetCode_315;

import java.util.HashMap;
import java.util.HashSet;

public class B {
    public int countDistinctIntegers(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            StringBuilder sb = new StringBuilder();
            sb.append(num);
            int reverse = Integer.parseInt(sb.reverse().toString());
            set.add(reverse);
        }
        return set.size();
    }

}
