package LeetCode;

public class 素数 {
    public static final int MAXN = 10000;
    public int[] visit = new int[MAXN];


    /**
     * 埃氏筛,从2开始，将每个质数的倍数都标记成合数，以达到筛选素数的目的.
     * 埃氏筛法的缺陷 ：对于一个合数，有可能被筛多次。例如 30 = 2 * 15 = 3 * 10 = 5*6……
     * 那么如何确保每个合数只被筛选一次呢？我们只要用它的最小质因子来筛选即可，这便是欧拉筛法。
     */
    public void prime() {
        //0 和 1不是素数
        visit[0] = 1;
        visit[1] = 1;
        for (int i = 2; i <= MAXN; i++) {
            if (visit[i] == 0) {
                for (int j = i * i; j <= MAXN; j+=i) {
                    visit[j] = 1;
                }
            }
        }
    }

    public int[] prime = new int[MAXN];
    /**
     * 欧拉筛。在埃氏筛法的基础上，让每个合数只被它的最小质因子筛选一次，以达到不重复目的。
     */
    public void eulerPrime() {
        int cnt = 0;
        for (int i = 2; i <= MAXN; i++) {
            if (visit[i] == 0) {
                prime[cnt++] = i;
            }
            //i当作最小质因子的倍数。
            for (int j = 0; j <= cnt && i * prime[j] <= MAXN; j++) {
                visit[i * prime[j]] = 1;
                //i = k * prime[j], i * prime[j + 1] = k * prime[j] * prime[j + 1]，出现重复
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
    }
}
