package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

public class _56 {
    public int[][] merge(int[][] intervals) {
        ArrayList<ArrayList<Integer>> vec = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int start = intervals[0][0];
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i ++) {
            //进行区间合并
            if(intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            }else {
                ArrayList<Integer> tem = new ArrayList<>();
                tem.add(start);
                tem.add(end);
                vec.add(tem);
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        ArrayList<Integer> tem = new ArrayList<>();
        tem.add(start);
        tem.add(end);
        vec.add(tem);
        int[][] ans = new int[vec.size()][2];
        for(int i = 0; i < vec.size(); i ++) {
            ans[i][0] = vec.get(i).get(0);
            ans[i][1] = vec.get(i).get(1);
        }
        return ans;
    }


}
