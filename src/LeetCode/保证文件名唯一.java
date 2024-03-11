package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 保证文件名唯一 {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> index = new HashMap<>();
        int n = names.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            String name = names[i];
            if (!index.containsKey(name)) {
                res[i] = name;
                index.put(name, 1);
            } else {
                int k = index.get(name);
                while (index.containsKey(addSuffix(name, k))) {
                    k++;
                }
                res[i] = addSuffix(name, k);
                index.put(name, k + 1);
                index.put(addSuffix(name, k), 1);
            }
        }
        return res;
    }

    public String addSuffix(String name, int k) {
        return name + "(" + k + ")";
    }


    public static void main(String[] args) {
        String regex = "^([\\s\\S]*)(\\(([1-9][0-9]*)\\))$";
        Pattern pattern = Pattern.compile(regex);
        String s = "kaido(1)(1)";
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
