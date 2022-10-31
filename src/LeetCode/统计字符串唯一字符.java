package LeetCode;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 统计字符串唯一字符 {
    HashMap<Character, Integer[]> map = new HashMap<>();
    public int uniqueLetterString(String s) {
        //我们不妨设dp[i]代表0~i字符串的唯一字符的个数总和
        //对于i + 1个字符,我们需要知道在0 ~ i中倒数第一出线和倒数第二次出现的下标(如果不存在则为-1);
        if (s.length() == 1) {
            return 1;
        }
        int n = s.length();
        int[] dp = new int[n];
        //init the map
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            Integer[] array = {-1, -1};
            map.put(ch, array);
        }
        dp[0] = 1;
        dp[1] = (s.charAt(1) == s.charAt(0)) ? 2 : 4;
        for (int i = 0; i <= 1; i++) {
            char ch = s.charAt(i);
            updateIndex(ch, i);
        }
        for (int i = 2; i < n; i++) {
            //我们需要找出以前一个字符结尾的子字符串数量
            int uniqueLetterCount = dp[i - 1] - dp[i - 2];
            char ch = s.charAt(i);
            Integer[] array = map.get(ch);
            if (array[0] == -1) {
                dp[i] = dp[i - 1] + uniqueLetterCount + i + 1;
            } else if (array[1] == -1) {
                dp[i] = dp[i - 1] + uniqueLetterCount + (i - array[0] - 1) - (array[0] + 1) + 1;
            } else {
                dp[i] = dp[i - 1] + uniqueLetterCount + (i - array[0] - 1) - (array[0] - array[1]) + 1;
            }
            updateIndex(ch, i);
        }
        return dp[n - 1];
    }

    public void updateIndex(char ch, int index) {
        Integer[] array = map.get(ch);
        if (array[0] != -1) {
            array[1] = array[0];
        }
        array[0] = index;
    }

    /**
     * 对于下标为j的字符c，当它在某个子字符串中仅出现一次，它会对统计有贡献
     * 只需对每个字符，计算有多少子字符串仅包含该字符一次即可。
     * 对于c，记上次出现的位置是i,记下次出现的位置是k，那么这样的字符串有(j - i) * (k - j)
     * @param s
     * @return
     */
    public int uniqueLetterString1(String s) {
        Map<Character, List<Integer>> index = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!index.containsKey(c)) {
                index.put(c, new ArrayList<Integer>());
                index.get(c).add(-1);
            }
            index.get(c).add(i);
        }
        int res = 0;
        for (Map.Entry<Character, List<Integer>> entry : index.entrySet()) {
            List<Integer> arr = entry.getValue();
            arr.add(s.length());
            for (int i = 1; i < arr.size() - 1; i++) {
                res += (arr.get(i) - arr.get(i - 1)) * (arr.get(i + 1) - arr.get(i));
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String s = "ABABA";
        new 统计字符串唯一字符().uniqueLetterString(s);
    }
}
