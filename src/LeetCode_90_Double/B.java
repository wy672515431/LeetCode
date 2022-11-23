package LeetCode_90_Double;

import java.util.ArrayList;
import java.util.List;

public class B {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> list = new ArrayList<>();
        for (String query : queries) {
            boolean ok = false;
            for (String dic : dictionary) {
                int diff = 0;
                for (int i = 0; i < query.length(); i++) {
                    if (query.charAt(i) != dic.charAt(i)) {
                        diff++;
                    }
                }
                if (diff <= 2) {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                list.add(query);
            }
        }
        return list;
    }
}
