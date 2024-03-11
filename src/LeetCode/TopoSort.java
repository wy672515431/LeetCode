package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopoSort {
    List<ArrayList<Integer>> edges = new ArrayList<>();
    int[] inDegree;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            edges.get(edge[1]).add(edge[0]);
            inDegree[edge[0]]++;
        }
        return topoSort();
    }

    /**
     * 拓扑排序，我们需要知道每个顶点的入度。
     * 如果能够进行拓扑排序则表示能够完成，否则不能完成
     * 拓扑排序的思路:首先找到入度为0的点将其放入队列，然后将队列的一个点退出，并更新邻接点的入度。
     */
    public boolean topoSort() {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int source = queue.poll();
            // 更新
            count++;
            for (int target : edges.get(source)) {
                inDegree[target]--;
                if (inDegree[target] == 0) {
                    queue.offer(target);
                }
            }
        }
        return count == inDegree.length;
    }
}


class ParallelCourse {
    List<List<Integer>> edges = new ArrayList<>();
    int[] inDegrees;
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        inDegrees = new int[n + 1];
        // init the edges
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        // construct the map
        for (int[] relation : relations) {
            int u = relation[0];
            int v = relation[1];
            edges.get(u).add(v);
            inDegrees[v]++;
        }
        int ans = 0;
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegreesCopy = new int[n + 1];
        System.arraycopy(inDegrees, 0, inDegreesCopy, 0, n + 1);
        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // 每一轮最多只能读k个且如果遇到初态入度不为0时，也需要终止。
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < Math.min(k, size); i++) {
                int source = queue.poll();
                for (int target : edges.get(source)) {
                    inDegrees[target]--;
                    if (inDegrees[target] == 0) {
                        queue.offer(target);
                    }
                }
            }
        }
        return ans;
    }
}


class ParallelCourseIII {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> edges = new ArrayList<>();
        int[] inDegrees = new int[n + 1];
        // 编号从1开始
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] relation : relations) {
            int u = relation[0];
            int v = relation[1];
            edges.get(u).add(v);
            inDegrees[v]++;
        }
        // 我们需要加入时间信息， 一个课程能够学习的前提是，其所有前置课程都已经学完
        // 设当前课程为c，则上完该课程所需的时间为t(c) = max(t(p1), t(p2), ..., t(pk)) + time(c)
        Queue<Integer> queue = new LinkedList<>();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
                dp[i] = time[i - 1];
            }
        }
        while (!queue.isEmpty()) {
            int source = queue.poll();
            for (int target: edges.get(source)) {
                inDegrees[target]--;
                if (inDegrees[target] == 0) {
                    queue.offer(target);
                }
                dp[target] = Math.max(dp[target], dp[source] + time[target - 1]);
            }
        }
        int ans = 0;
        for (int num : dp) {
            ans = Math.max(ans, num);
        }
        return ans;
    }
}
