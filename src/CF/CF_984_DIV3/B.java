package CF.CF_984_DIV3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class B {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            String[] input = bf.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            Map<Integer, Integer> map = new HashMap<>();
            int[][] arr = new int[k][2];
            for (int i = 0; i < k; i++) {
                arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            for (int i = 0; i < k; i++) {
                map.put(arr[i][0], map.getOrDefault(arr[i][0], 0) + arr[i][1]);
            }
            List<Integer> values = new ArrayList<>(map.values());
            values.sort(Comparator.reverseOrder());
            int ans = 0;
            for (int i = 0; i < Math.min(n, values.size()); i++) {
                ans += values.get(i);
            }
            System.out.println(ans);
        }
        bf.close();
    }
}
