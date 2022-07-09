package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;

public class _2055 {
    //复杂度O(nlgn)
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int[] ans = new int[queries.length];
        int[] sum = new int[s.length() + 1];
        ArrayList<Integer> posList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                sum[i + 1] = sum[i] - 1;
            } else {
                sum[i + 1] = sum[i];
                posList.add(i);
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            int start = lowerBound(posList, left);
            int end = upperBound(posList, right);
            int num = 0;
            if (start < end) {
                num = -(sum[posList.get(end - 1) + 1] - sum[posList.get(start) + 1]);
            } else {
                num = 0;
            }

            ans[i] = num;
        }
        return ans;
    }

    //复杂度On
    public int[] platesBetweenCandles1(String s, int[][] queries) {
        int n = s.length();
        int[] preSum = new int[n];
        for (int i = 0, sum = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                sum++;
            }
            preSum[i] = sum;
        }
        int[] left = new int[n];
        for (int i = 0, l = -1; i < n; i++) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }
        int[] right = new int[n];
        for (int i = n - 1, r = -1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int x = right[query[0]], y = left[query[1]];
            ans[i] = x == -1 || y == -1 || x >= y ? 0 : preSum[y] - preSum[x];
        }
        return ans;
    }


    /**
     * 返回的是比Pos大或等的第一个值
     *
     * @param posList
     * @param pos
     * @return
     */
    private static int lowerBound(ArrayList<Integer> posList, int pos) {
        int start = 0;
        int end = posList.size() - 1;
        int mid;
        if(start >= end) {
            return -1;
        }
        while (start < end) {
            mid = (end - start) / 2 + start;
            if (posList.get(mid) >= pos) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * 返回的是比pos大的第一个值
     *
     * @param posList
     * @param pos
     * @return
     */
    private static int upperBound(ArrayList<Integer> posList, int pos) {
        int start = 0;
        int end = posList.size() - 1;
        int mid;
        if(start >= end) {
            return -1;
        }
        if(pos >= posList.get(end)) {
            return end + 1;
        }
        while (start < end) {
            mid = (end - start) / 2 + start;
            if (posList.get(mid) <= pos) {
                start = mid + 1;
            } else {
                end = mid ;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        String s = "**|**|***|";
        int[][] queries = new int[][]{
//                {1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}
                {2,5}, {5, 9}
        };
        platesBetweenCandles(s, queries);
    }
}
