package bytedance.math;

import java.util.ArrayList;
import java.util.List;

public class 格雷码 {
    // 假定已经有了n - 1位的格雷码，我们要做的就是在前面加一位，置为1即可
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(ans.get(j) | (1 << i));
            }
        }
        return ans;
    }
}
