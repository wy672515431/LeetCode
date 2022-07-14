package LeetCode_301;

import java.util.HashSet;
import java.util.Set;

public class B {
    int curMin;
    Set<Integer> set;
    public B() {
        this.curMin = 1;
        this.set = new HashSet();
    }

    public int popSmallest() {
        int res = curMin;
        set.add(curMin);
        while (set.contains(curMin)) {
            curMin++;
        }
        return res;
    }

    public void addBack(int num) {
        if (set.contains(num)) {
            if (num < curMin) {
                curMin = num;
            }
            set.remove(num);
        }
    }
}
