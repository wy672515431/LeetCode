package bytedance.图;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 字符串转化 {
    public static void main(String[] args) {
        字符串转化 main = new 字符串转化();
        System.out.println(main.canConvert("aabcc", "ccdee"));
    }
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }

        Map<Character, Character> conversionMappings = new HashMap<>();
        Set<Character> uniqueCharactersInStr2 = new HashSet<>();

        // 确保 str1 中的任何字符都没有映射到 str2 中的多个字符。
        for (int i = 0; i < str1.length(); i++) {
            if (!conversionMappings.containsKey(str1.charAt(i))) {
                conversionMappings.put(str1.charAt(i), str2.charAt(i));
                uniqueCharactersInStr2.add(str2.charAt(i));
            } else if (conversionMappings.get(str1.charAt(i)) != str2.charAt(i)) {
                // 此字母映射到两个不同的字符，因此 str1 无法转换为 str2。
                return false;
            }
        }

        // str1 中没有字符映射到 str2 中的两个或多个不同字符
        // 并有至少一个可用于中断任何循环的临时字符。
        if (uniqueCharactersInStr2.size() < 26) {
            return true;
        }

        // 转换映射形成一个或多个循环，并且没有可用于中断循环的临时字符，因此 str1 无法转换为 str2。
        return false;
    }
}
