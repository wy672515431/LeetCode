package bytedance;

import java.util.*;

public class 划分字母区间 {
    public List<Integer> partitionLabels(String s) {
        boolean[] hasOccur = new boolean[26];
        HashMap<Integer, ArrayList<Integer>> mMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (!hasOccur[index]) {
                // 起始位置
                hasOccur[index] = true;
                ArrayList<Integer> posList = mMap.getOrDefault(index, new ArrayList<>());
                posList.add(i);
                mMap.put(index, posList);
            } else {
                // mMap只记录了第一次出现的位置和最后一次出现的位置
                mMap.get(index).set(1, i);
            }
        }
        // 按照各个字母的起始位置排序
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(mMap.values());
        list.sort(Comparator.comparingInt(ArrayList::getFirst));
        List<Integer> ans = new ArrayList<>();
        int start = list.getFirst().getFirst();
        int end = list.getFirst().get(1);
        // 类似于区间合并
        for (int i = 1; i < list.size(); i++) {
            // 存在交集，不能划分
            if(list.get(i).get(0) < end) {
                end = Math.max(end, list.get(i).get(1));
            } else {
                // 不存在交集，可以划分
                ans.add(end - start + 1);
                start = list.get(i).get(0);
                end = list.get(i).get(1);
            }
        }
        ans.add(end - start + 1);
        return ans;
    }
}
