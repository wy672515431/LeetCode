package bytedance.图;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 课程表II {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            inDegrees[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int[] ans = new int[numCourses];
        int counter = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            ans[counter++] = course;
            for (int v : graph.get(course)) {
                inDegrees[v]--;
                if (inDegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return counter == numCourses ? ans : new int[0];
    }
}
