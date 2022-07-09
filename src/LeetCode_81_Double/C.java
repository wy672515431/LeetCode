package LeetCode_81_Double;

import java.util.HashSet;

public class C {
    public int maximumXOR(int[] nums) {
        int maxLen = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            String str = Integer.toBinaryString(nums[i]);
            maxLen = Math.max(maxLen, str.length());
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1') {
                    set.add(str.length() - 1 - j);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            if (set.contains(maxLen - 1 - i)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    public int maximumXOR1(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            ans = ans | nums[i];
        }
        return ans;
    }
}
