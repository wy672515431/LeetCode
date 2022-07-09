package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class 杨辉三角 {
    /**
     * 这题最关键的是要倒着计算
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(1);
        for(int i = 1; i <= rowIndex; i++) {
            ans.add(0);
            for(int j = i; j > 0; j--) {
                ans.set(j, ans.get(j) + ans.get(j - 1));
            }
        }
        return ans;
    }
}
