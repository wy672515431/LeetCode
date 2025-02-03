package LeetCode_418;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class B {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] invocation : invocations) {
            graph.get(invocation[0]).add(invocation[1]);
        }
        Set<Integer> targetMethods = new HashSet<>();
        boolean[] isVisited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k);
        while (!queue.isEmpty()) {
            int method = queue.poll();
            if (isVisited[method]) {
                continue;
            }
            isVisited[method] = true;
            targetMethods.add(method);
            for (int nextMethod : graph.get(method)) {
                queue.offer(nextMethod);
            }
        }
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> methods = graph.get(i);
            if (targetMethods.contains(i)) {
                continue;
            }
            for (int method : methods) {
                if (targetMethods.contains(method)) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                break;
            }
        }
        List<Integer> res = new ArrayList<>();
        if (!ok) {
            for (int i = 0; i < n; i++) {
                res.add(i);
            }
            return res;
        } else {
            for (int i = 0; i < n; i++) {
                if (!targetMethods.contains(i)) {
                    res.add(i);
                }
            }
            return res;
        }
    }
}
