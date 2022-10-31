package test;

import java.util.*;

public class A {
    public static void main(String[] args) {

    }
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] ans = new int[n];
        Node[] nodeArray = new Node[n];
        for (int i = 0; i < n; i++) {
            nodeArray[i] = new Node(intervals[i][0], intervals[i][1], i);
        }
        Arrays.sort(nodeArray, (o1, o2) -> o1.start - o2.start);
        for (int i = 0; i < n; i++) {
            int index = lowerBound(nodeArray, intervals[i][1]);
            if (index == n) {
                ans[i] = -1;
            } else {
                ans[i] = nodeArray[index].index;
            }
        }
        return ans;
    }

    public int lowerBound(Node[] nodeArray, int end) {
        int low = 0;
        int high = nodeArray.length - 1;
        //end大于任何start
        if (end > nodeArray[high].start) {
            return high + 1;
        }
        int mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (nodeArray[mid].start >= end) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}

class Node {
    int start;
    int end;
    int index;
    Node(int start, int end, int index) {
        this.start = start;
        this.end = end;
        this.index = index;
    }
}




