package LeetCode_83_Double;

import java.util.HashMap;

public class A {
    public String bestHand(int[] ranks, char[] suits) {
        HashMap<Integer, Integer> map = new HashMap();
        boolean isFlush = true;
        for (int i = 0; i < 4; i++) {
            if (suits[i] != suits[i + 1]) {
                isFlush = false;
            }
        }
        if (isFlush) {
            return "Flush";
        } else {
            for (int i = 0; i < 5; i++) {
                Integer val = map.getOrDefault(ranks[i], 0);
                map.put(ranks[i], val + 1);
            }
            int maxVal = 0;
            for (Integer val : map.values()) {
                maxVal = Math.max(maxVal, val);
            }
            if (maxVal >= 3) {
                return "Three of a Kind";
            } else if (maxVal >= 2) {
                return "Pair";
            } else {
                return "High Card";
            }
        }
    }
}
