package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class 匹配子序列的单词数 {
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] list = new List[26];
        for (int i = 0; i < 26; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); i++) {
            list[s.charAt(i) - 'a'].add(i);
        }
        int ans = words.length;
        for (String word : words) {
            if (word.length() > s.length()) {
                ans--;
                continue;
            }
            int target = -1;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (list[ch - 'a'].isEmpty() || list[ch - 'a'].get(list[ch - 'a'].size() - 1) <= target) {
                    ans--;
                    break;
                }
                target = binarySearch(list[ch - 'a'], target);
            }
        }
        return ans;
    }

    public int binarySearch(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        int mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (list.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return list.get(low);
    }
}
