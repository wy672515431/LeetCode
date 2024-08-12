package bytedance.递归;

import java.util.ArrayList;
import java.util.List;

public class 花括号展开 {
    List<List<String>> sections = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public String[] expand(String s) {
        getSections(s);
        dfs(0);
        ans.sort(String::compareTo);
        return ans.toArray(String[]::new);
    }

    private void dfs(int sectionIndex) {
        if (sectionIndex == sections.size()) {
            ans.add(sb.toString());
            return;
        }
        List<String> section = sections.get(sectionIndex);
        for (String s : section) {
            sb.append(s);
            dfs(sectionIndex + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private void getSections(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '{') {
                List<String> section = new ArrayList<>();
                int j = i + 1;
                for (; j < s.length(); j++) {
                    char ch1 = s.charAt(j);
                    if (ch1 == '}') {
                        sections.add(section);
                        break;
                    }
                    if (Character.isLetter(ch1)) {
                        section.add("" + ch1);
                    }
                }
                i = j;
            } else if (Character.isLetter(ch)) {
                List<String> section = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                int j = i;
                while (j < s.length() && Character.isLetter(s.charAt(j))) {
                    sb.append(s.charAt(j));
                    j++;
                }
                section.add(sb.toString());
                sections.add(section);
                i = j - 1;
            }
        }
    }

}
