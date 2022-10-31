package LeetCode_84_Double;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < items1.length; i++) {
            int val = map.getOrDefault(items1[i][0], 0);
            map.put(items1[i][0], val + items1[i][1]);
        }
        for (int i = 0; i < items2.length; i++) {
            int val = map.getOrDefault(items2[i][0], 0);
            map.put(items2[i][0], val + items2[i][1]);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            List<Integer> tem = new ArrayList<>();
            tem.add(entry.getKey());
            tem.add(entry.getValue());
            ans.add(tem);
        }
        Collections.sort(ans, (o1, o2) -> o1.get(0) - o2.get(0));
        return ans;
    }
}
