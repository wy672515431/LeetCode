package bytedance.数组;

import java.util.ArrayList;
import java.util.List;

public class 插入区间 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<List<Integer>> res = new ArrayList<>();
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        boolean ok = true;
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            //
            if (end < newStart) {
                List<Integer> list = new ArrayList<>();
                list.add(start);
                list.add(end);
                res.add(list);
            } else if (start > newEnd) {
                if (ok) {
                    List<Integer> list = new ArrayList<>();
                    list.add(newStart);
                    list.add(newEnd);
                    res.add(list);
                    ok = false;
                }
                List<Integer> list = new ArrayList<>();
                list.add(start);
                list.add(end);
                res.add(list);
            } else {
                // 融合
                newStart = Math.min(start, newStart);
                newEnd = Math.max(end, newEnd);
                ok = true;
            }
        }
        if (ok) {
            List<Integer> list = new ArrayList<>();
            list.add(newStart);
            list.add(newEnd);
            res.add(list);
        }
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i][0] = res.get(i).get(0);
            ans[i][1] = res.get(i).get(1);
        }
        return ans;
    }
}
