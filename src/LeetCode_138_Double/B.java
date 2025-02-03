package LeetCode_138_Double;

public class B {
    public String stringHash(String s, int k) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n / k; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum += (s.charAt(i * k + j) - 'a');
            }
            sum %= 26;
            res.append((char) (sum + 'a'));
        }
        return res.toString();
    }
}
