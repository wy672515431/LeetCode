package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合intervals，其中 intervals[i] = [starti, endi]。返回 需要移除区间的最小数量，使剩余区间互不重叠。
 *
 */
public class 无重叠区间 {
    /**
     * 贪心算法
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if(o1[0] == o2[0])
                return o1[1] - o2[1];
            else
                return o1[0] - o2[0];
        });
        int ans = 0;
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
               if(intervals[i][0] >= end) {
                   end = intervals[i][1];
               }else{
                   if(intervals[i][1] <= end) {
                       end = intervals[i][1];
                       ans++;
                   }else {
                       ans++;
                   }
               }
        }
        return ans;
    }
}
