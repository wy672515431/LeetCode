package LeetCode_317;

import java.util.*;

public class B {
    /**
     *  返回总播放量最多的作者，以及其播放量最高的id
     * @param creators 第i个视频的创作者
     * @param ids 第i个视频的id
     * @param views 第i个视频的播放量
     * @return
     */
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Integer> creatorsMap = new TreeMap<>();
        Map<String, String> idsMap = new HashMap<>();
        Map<String, Integer> viewsMap = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        int n = creators.length;
        for (int i = 0; i < n; i++) {
            creatorsMap.put(creators[i],
                    views[i] + creatorsMap.getOrDefault(creators[i], 0));
            if (viewsMap.get(creators[i]) == null || views[i] > views[viewsMap.get(creators[i])]) {
                viewsMap.put(creators[i], i);
                idsMap.put(creators[i], ids[i]);
            } else if (views[i] == views[viewsMap.get(creators[i])]) {
                if (ids[i].compareTo(idsMap.get(creators[i])) < 0) {
                    viewsMap.put(creators[i], i);
                    idsMap.put(creators[i], ids[i]);
                }
            }
        }
        List<Map.Entry<String , Integer>> list = new ArrayList<>(creatorsMap.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        int max = list.get(0).getValue();
        for (Map.Entry<String , Integer> entry : list) {
            if (entry.getValue() == max) {
                List<String> tem = new ArrayList<>();
                tem.add(entry.getKey());
                tem.add(idsMap.get(entry.getKey()));
                ans.add(tem);
            } else {
                break;
            }
        }
        return ans;
    }
}
