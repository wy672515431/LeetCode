package LeetCode;

/**
 * 一位老师正在出一场由 n道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F'表示）。
 * 老师想增加学生对自己做出答案的不确定性，方法是最大化有连续相同结果的题数。（也就是连续出现 true 或者连续出现 false）。
 *
 * 给你一个字符串answerKey，其中answerKey[i]是第 i个问题的正确结果。除此以外，还给你一个整数 k，表示你能进行以下操作的最多次数：
 *
 * 每次操作中，将问题的正确答案改为'T' 或者'F'（也就是将 answerKey[i] 改为'T'或者'F'）。
 * 请你返回在不超过 k次操作的情况下，最大连续 'T'或者 'F的数目。
 */
public class 考试的最大困扰度 {
    /**
     * 修改次数不超过k次，连续段'T'或'F‘的最大长度
     * 等价于求一个包含'F'或者'T'的个数不超过k的最大长度窗口
     * 使用 j 和 i 分别代表窗口的左右端点
     * cnt为区间[j,i] 中的字符 c 的数量，每次右端点 i 移动时，若满足 s[i]=c，
     * 让 cnt 自增，当 cnt >k 时，使左端点 j 往右移动，同时更新 cnt，直到 [j, i] 间恢复合法性（
     * @param answerKey
     * @param k
     * @return
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        return Math.max(getNums(answerKey, n , 'T', k), getNums(answerKey, n, 'F', k));
    }

    private int getNums(String answerKey, int length, char ch, int k) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int ans = 0;
        for (; right < length; right++) {
            sum += answerKey.charAt(right) == ch ? 1 : 0;
            while (sum > k) {
                sum -= answerKey.charAt(left) == ch ? 1 : 0;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
