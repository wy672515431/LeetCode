package bytedance.图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 课程表 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        List<List<Integer>> edges = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] prerequisite: prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int prerequisite = queue.poll();
            count++;
            for (int course : edges.get(prerequisite)) {
                inDegree[course]--;
                if (inDegree[course] == 0) {
                    queue.offer(course);
                }
            }
        }
        return count == numCourses;
    }
}
