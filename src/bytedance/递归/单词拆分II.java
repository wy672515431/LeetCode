package bytedance.递归;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 单词拆分II {
    List<List<String>> res = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    Map<String, List<List<String>>> map = new HashMap<>();
    Set<String> set = new HashSet<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        dfs(s, 0);
        List<String> ans = new ArrayList<>();
        for (List<String> list : res) {
            ans.add(String.join(" ", list));
        }
        return ans;
    }

    private void dfs(String s, int index) {
        if (index == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String substr = s.substring(index, i + 1);
            if (set.contains(substr)) {
                temp.add(substr);
                dfs(s, i + 1);
                temp.removeLast();
            }
        }
    }
}
