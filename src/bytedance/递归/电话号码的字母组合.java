package bytedance.递归;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 电话号码的字母组合 {
    Map<Integer, char[]> mapping = new HashMap<>();
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        initMapping();
        solve(digits, 0);
        return res;
    }

    private void solve(String digits, int index) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char[] mappingChars = mapping.get(digits.charAt(index) - '0');
        for (char mappingChar : mappingChars) {
            sb.append(mappingChar);
            solve(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private void initMapping() {
        mapping.put(2, new char[]{'a', 'b', 'c'});
        mapping.put(3, new char[]{'d', 'e', 'f'});
        mapping.put(4, new char[]{'g', 'h', 'i'});
        mapping.put(5, new char[]{'j', 'k', 'l'});
        mapping.put(6, new char[]{'m', 'n', 'o'});
        mapping.put(7, new char[]{'p', 'q', 'r', 's'});
        mapping.put(8, new char[]{'t', 'u', 'v'});
        mapping.put(9, new char[]{'w', 'x', 'y', 'z'});
    }
}
