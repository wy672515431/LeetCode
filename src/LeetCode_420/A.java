package LeetCode_420;

import java.util.ArrayList;
import java.util.List;

public class A {
    public List<String> stringSequence(String target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            sb.append('a');
            res.add(sb.toString());
            for (char j = 'b'; j <= ch; j++) {
                sb.setCharAt(sb.length() - 1, j);
                res.add(sb.toString());
            }
        }
        return res;
    }
}
