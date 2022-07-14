package LeetCode_301;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class D {
    static final int mod = 1000000007;
    static final int maxPrimeFactor = 13;
    List<Integer>[] factorNumList;
    /**
     * 不妨考虑maxValue = 12
     * 我们考虑以12为结尾的序列
     * 12 = 2 * 2 * 3
     * 1 2 12
     * 1 2 4 12
     * 1 2 6 12
     * 1 3 6 12
     * 则序列前面都是由12的质因子乘积组成
     *
     * 问题等价于有k个相同小球，放到n个不同的盒子里，盒子允许为空，组合数为多少
     * 我们首先考虑盒子不允许为空的情况
     * 将k个球放在一起，在k - 1个空位中放入n - 1个隔板
     * C(k - 1, n - 1)
     * 上述问题等价于(n + k)
     * C(n + k - 1, n - 1) = C(n + k - 1, k)
     * C(n, m) = C(n - 1, m - 1) + C(n - 1, m)
     * 题目中数最大为10000，可以最多分解13个质因子
     * @param n
     * @param maxValue
     * @return
     */
    public int idealArrays(int n, int maxValue) {
        factorNumList = new List[maxValue + 1];
        for (int i = 1; i <= maxValue; i++) {
            int tem = i;
            factorNumList[i] = new ArrayList<Integer>();
            //求每个质因数的个数
            for (int p = 2; p * p <= tem; p++) {
                if (tem % p == 0) {
                    int count = 0;
                    for(; tem % p == 0; tem /= p) {
                        count++;
                    }
                    factorNumList[i].add(count);
                }
            }
            if (tem > 1) {
                factorNumList[i].add(1);
            }
        }
        //组合数 i代表序列长度,j代表质因数个数
        int[][] combine = new int[n + maxPrimeFactor + 1][maxPrimeFactor + 1];
        int len = combine.length;
        combine[0][0] = 1;
        for (int i = 1; i < len; i++) {
            combine[i][0] = 1;
            for (int j = 1; j <= Math.min(i, maxPrimeFactor); j++) {
                combine[i][j] = (combine[i - 1][j - 1] + combine[i - 1][j]) % mod;
            }
        }

        long ans = 0;
        for (int i = 1; i <= maxValue; i++) {
            long mul = 1L;
            for (int k : factorNumList[i]) {
                mul = mul * combine[n + k -1][k] % mod;
            }
            ans += mul;
        }
        return (int)(ans % mod);
    }
}
