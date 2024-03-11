package LeetCode_346;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class D {
    // k - node v - parent
    Map<Integer, Integer> map = new HashMap<>();

    public double frogPosition(int n, int[][] edges, int t, int target) {
        ArrayList<Integer>[] edgesArray = new ArrayList[n];
        boolean[] isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            edgesArray[i] = new ArrayList<>();
        }
        // 无向图
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            edgesArray[u].add(v);
            edgesArray[v].add(u);
        }
        // 判断从根节点到target节点是否存在一条路径
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        map.put(0, -1);
        int len = 0;
        // 判断是否可达
        boolean isReachable = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int u = queue.poll();
                size--;
                isVisited[u] = true;
                // 目标节点
                if (u == target - 1) {
                    // 恰好
                    if (len == t) {
                        isReachable = true;
                    } else if (len < t) {
                        if (u == 0 && edgesArray[u].size() == 0) {
                            isReachable = true;
                        } else if (u != 0 && edgesArray[u].size() == 1) {
                            isReachable = true;
                        }
                    }
                    break;
                } else {
                    for (int v : edgesArray[u]) {
                        if (!isVisited[v]) {
                            map.put(v, u);
                            queue.offer(v);
                        }
                    }
                }
            }
            len++;
        }
        if (!isReachable) {
            return 0.0;
        }
        int node = target - 1;
        double ans = 1.0;
        while (node != -1) {
            node = map.get(node);
            if (node != -1) {
                if (node == 0) {
                    ans /= edgesArray[node].size();
                } else {
                    ans /= edgesArray[node].size() - 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        D d = new D();
        int[][] grid = {{0,0,0,0}, {1,0,0,1},{0,1,0,0},{0,0,0,0}};
        d.shortestPathBinaryMatrix(grid);
    }

    // up down left right
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1) {
            return -1;
        }
        if (grid[n - 1][n - 1] == 1) {
            return -1;
        }
        boolean[][] isVisited = new boolean[n][n];
        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(0, 0);
        isVisited[0][0] = true;
        queue.offer(start);
        int ans = -1;
        int temp = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                int x = node.x;
                int y = node.y;
                if (x == n - 1 && y == n - 1) {
                    ans = temp;
                    return ans;
                }
                for (int[] direction : directions) {
                    int sx = direction[0];
                    int sy = direction[1];
                    if (check(x, y, sx, sy, n, grid) && !isVisited[x + sx][y + sy]) {
                        isVisited[x + sx][y + sy] = true;
                        queue.offer(new Node(x + sx, y + sy));
                    }
                }
            }
            temp++;
        }
        return ans;
    }

    public boolean check(int x, int y, int sx, int sy, int n, int[][] grid) {
        int nx = x + sx;
        int ny = y + sy;
        if (nx < 0 || nx >= n) {
            return false;
        }
        if (ny < 0 || ny >= n) {
            return false;
        }
        if (grid[nx][ny] != 0) {
            return false;
        }
        return true;
    }

    class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
