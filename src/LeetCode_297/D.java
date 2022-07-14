package LeetCode_297;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class D {
    public long distinctNames(String[] ideas) {
        long ans = 0;
        HashSet<String> set = new HashSet<>();
        //不能交换地首字母
        HashMap<String, HashSet<Character>> map = new HashMap<>();
        HashMap<Character, HashMap<Character, Integer>> map1 = new HashMap();
        for (int i = 0; i < 26; i++) {
            char ch = (char)(i + 'a');
            map1.put(ch, new HashMap());
        }
        for (int i = 0; i < ideas.length; i++) {
            map.put(ideas[i], new HashSet<>());
        }
        int[] counts = new int[26];
        for (int i = 0; i < ideas.length; i++) {
            char firstCharacter = ideas[i].charAt(0);
            counts[firstCharacter - 'a'] ++;
        }
        for (int i = 0; i < ideas.length; i++) {
            set.add(ideas[i]);
        }
        for (int i = 0; i < ideas.length; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                sb.append(ideas[i].substring(1, ideas[i].length()));
                if (set.contains(sb.toString())) {
                    map.get(ideas[i]).add(ch);
                    Map<Character, Integer> mMap = map1.get(ch);
                    Integer val = mMap.putIfAbsent(ideas[i].charAt(0), 1);
                    if (val != null) {
                        mMap.put(ideas[i].charAt(0), val + 1);
                    }
                }
            }
        }
        int sum = Arrays.stream(counts).sum();
        for (int i = 0; i < ideas.length; i++) {
            int tem = sum;
            for (Character ch : map.get(ideas[i])) {
                tem -= counts[ch - 'a'];
            }
            for (Map.Entry<Character, Integer> entry : map1.get(ideas[i].charAt(0)).entrySet()) {
                if (!map.get(ideas[i]).contains(entry.getKey())) {
                    tem -= entry.getValue();
                }
            }
            ans = ans + 1L * tem;
        }
        return ans;
    }
}
