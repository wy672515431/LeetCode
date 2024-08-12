package bytedance.math;

import java.util.ArrayList;
import java.util.List;

public class 最小因式分解 {
    public int smallestFactorization(int num) {
        if (num == 1) {
            return num;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 9; i >= 2; i--) {
            while (num % i == 0) {
                res.add(i);
                num /= i;
            }
        }
        if (num > 1) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = res.size() - 1; i >= 0; i--) {
            sb.append(res.get(i));
        }
        if (Long.parseLong(sb.toString()) > Integer.MAX_VALUE) {
            return 0;
        }
        return Integer.parseInt(sb.toString());
    }
}
