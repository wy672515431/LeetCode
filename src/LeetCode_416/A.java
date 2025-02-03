package LeetCode_416;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class A {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> wordsSet = new HashSet<>(Arrays.asList(bannedWords));
        int cnt = 0;
        for (String word : message) {
            if (wordsSet.contains(word)) {
                cnt++;
            }
        }
        return cnt >= 2;
    }
}
