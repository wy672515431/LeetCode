package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 灯泡开关 {
    /**
     * 不需要考虑按钮按的顺序并且只需考虑每个按钮按了奇数次还是偶数次
     * 编号为 6k+1，受按钮 1,3,4影响；
     * 编号为 6k+2, ,6k+6，受按钮 1,2影响；
     * 编号为 6k+3, 6k+5受按钮 1,3 影响；
     * 编号为 6k+4，受按钮 1,2,4 影响
     * @param n
     * @param presses
     * @return
     */
        public int flipLights(int n, int presses) {
            if (presses == 0) return 1;
            if (n == 1) return 2;
            else if (n == 2) return presses == 1 ? 3 : 4;
            else return presses == 1 ? 4 : presses == 2 ? 7 : 8;
        }
}
