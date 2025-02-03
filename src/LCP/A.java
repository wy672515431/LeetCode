package LCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        List<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            String[] input = bf.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            List<Integer> curEdges = edges.get(i);
            int size = curEdges.size();
            ans += (long)(size - 1) * size / 2;
        }
        System.out.println(ans);
        bf.close();
    }
}
