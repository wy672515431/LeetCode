package LeetCode;

import java.util.*;

public class 划分字母区间 {
    public List<Integer> partitionLabels(String s) {
        boolean[] hasOccur = new boolean[26];
        HashMap<Integer, ArrayList<Integer>> mMap = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            if (!hasOccur[s.charAt(i) - 'a']) {
                hasOccur[s.charAt(i) - 'a'] = true;
                ArrayList<Integer> posList = new ArrayList();
                posList.add(i);
                mMap.put(s.charAt(i) - 'a', posList);
            } else {
                mMap.get(s.charAt(i) - 'a').set(1, i);
            }
        }
        ArrayList<ArrayList<Integer>> list = new ArrayList(mMap.values());
        list.sort(Comparator.comparingInt(o -> o.get(0)));
        List<Integer> ans = new ArrayList<>();
        int start = list.get(0).get(0);
        int end = list.get(0).get(1);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).get(0) < end) {
                end = Math.max(end, list.get(i).get(1));
            } else {
                ans.add(end - start + 1);
                start = list.get(i).get(0);
                end = list.get(i).get(1);
            }
        }
        ans.add(end - start + 1);
        return ans;
    }
}
