package bytedance.递归;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class 化学公式解析 {
    static int len;
    static int index = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String chemistry = scanner.next();
        len = chemistry.length();
        Map<String, Integer> countMap = handleChemistry(chemistry);
        StringBuilder ans = new StringBuilder();
        for (Map.Entry<String, Integer> entries : countMap.entrySet()) {
            ans.append(entries.getKey());
            ans.append(entries.getValue());
        }
        System.out.println(ans);
    }

    // 元素
    // ()
    // []
    private static Map<String, Integer> handleChemistry(String chemistry) {
        Map<String, Integer> countMap = new TreeMap<>(
                String::compareTo
        );
        while (index < len) {
            char ch = chemistry.charAt(index);
            // atom
            if (Character.isLetter(ch) && Character.isUpperCase(ch)) {
                countMap = handleAtom(chemistry, countMap);
            } else if (ch == '(') {
                countMap = handleGroup(chemistry, countMap);
            } else if (ch == '[') {
                countMap = handleAggregation(chemistry, countMap);
            }
        }

        return countMap;
    }
    private static Map<String, Integer> handleAtom(String chemistry, Map<String, Integer> parent) {
        Map<String, Integer> countMap = new TreeMap<>(
                String::compareTo
        );
        // 驼峰式命名，第一个字母大写
        StringBuilder sb = new StringBuilder();
        sb.append(chemistry.charAt(index++));
        int count = 0;
        if (index < len) {
            char ch = chemistry.charAt(index);
            // 后面的字母必须是小写
            while (index < len && Character.isLetter(ch) && Character.isLowerCase(ch)) {
                sb.append(ch);
                index++;
                if (index < len) {
                    ch = chemistry.charAt(index);
                }
            }
        }
        if (index < len) {
            char ch = chemistry.charAt(index);
            while (index < len && Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
                index++;
                if (index < len) {
                    ch = chemistry.charAt(index);
                }
            }
        }
        countMap.put(sb.toString(), count == 0 ? 1 : count);
        return merge(parent, countMap);
    }

    private static Map<String, Integer> handleGroup(String chemistry, Map<String, Integer> parent) {
        Map<String, Integer> countMap = new TreeMap<>(
                String::compareTo
        );
        assert chemistry.charAt(index) == '(';
        index++;
        while (index < len) {
            char ch = chemistry.charAt(index);
            // atom
            if (Character.isLetter(ch) && Character.isUpperCase(ch)) {
                countMap = handleAtom(chemistry, countMap);
            } else if (ch == '(') {
                countMap = handleGroup(chemistry, countMap);
            } else if (ch == '[') {
                countMap = handleAggregation(chemistry, countMap);
            } else if (ch == ')') {
                index++;
                break;
            }
        }
        // 数字
        int count = 0;
        if (index < len) {
            char ch = chemistry.charAt(index);
            while (index < len && Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
                index++;
                if (index < len) {
                    ch = chemistry.charAt(index);
                }
            }
        }
        for (String key : countMap.keySet()) {
            countMap.put(key, countMap.get(key) * (count == 0 ? 1 : count));
        }

        return merge(parent, countMap);
    }

    private static Map<String, Integer> handleAggregation(String chemistry, Map<String, Integer> parent) {
        Map<String, Integer> countMap = new TreeMap<>(
                String::compareTo
        );
        assert chemistry.charAt(index) == '[';
        index++;
        while (index < len) {
            char ch = chemistry.charAt(index);
            // atom
            if (Character.isLetter(ch) && Character.isUpperCase(ch)) {
                countMap = handleAtom(chemistry, countMap);
            } else if (ch == '(') {
                countMap = handleGroup(chemistry, countMap);
            } else if (ch == '[') {
                countMap = handleAggregation(chemistry, countMap);
            } else if (ch == ']') {
                index++;
                break;
            }
        }

        // 数字
        int count = 0;
        if (index < len) {
            char ch = chemistry.charAt(index);
            while (index < len && Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
                index++;
                if (index < len) {
                    ch = chemistry.charAt(index);
                }
            }
        }
        for (String key : countMap.keySet()) {
            countMap.put(key, countMap.get(key) * (count == 0 ? 1 : count));
        }
        return merge(parent, countMap);
    }

    private static Map<String, Integer> merge(Map<String, Integer> map1,
                                              Map<String, Integer> map2) {
        for (Map.Entry<String, Integer> entries : map2.entrySet()) {
            String key = entries.getKey();
            Integer val = entries.getValue();
            map1.put(key, map1.getOrDefault(key, 0) + val);
        }
        return map1;
    }
}
