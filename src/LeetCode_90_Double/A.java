package LeetCode_90_Double;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A {
    public String oddString(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < word.length(); i++) {
                sb.append(word.charAt(i) - word.charAt(i - 1)).append("#");
            }
            String builder = sb.deleteCharAt(sb.length() - 1).toString();
            List<String> orDefault = map.getOrDefault(builder, new ArrayList<>());
            orDefault.add(word);
            map.put(builder, orDefault);
        }
        String res = "";
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                res = entry.getValue().get(0);
                break;
            }
        }
        return res;
    }
}
