package bytedance.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 合并距离 {
    public int[][] merge(int[][] intervals) {
        List<List<Integer>> ans = new ArrayList<>();
        // 按照start从小到大进行排序
        Arrays.sort(intervals, (Comparator.comparingInt(o -> o[0])));
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];
            // 相交
            if (curStart <= end) {
                end = Math.max(end, curEnd);
            } else {
                // 不相交
                List<Integer> tmp = new ArrayList<>();
                tmp.add(start);
                tmp.add(end);
                ans.add(tmp);
                start = curStart;
                end = curEnd;
            }
        }
        List<Integer> tmp = new ArrayList<>();
        tmp.add(start);
        tmp.add(end);
        ans.add(tmp);
        int n = ans.size();
        int[][] res = new int[n][2];
        for (int i = 0; i < n; i++) {
            res[i][0] = ans.get(i).get(0);
            res[i][1] = ans.get(i).get(1);
        }
        return res;
    }
}
