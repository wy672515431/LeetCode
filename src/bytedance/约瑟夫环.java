package bytedance;

public class 约瑟夫环 {
    // n个人排成一个环、报到m的人出列，求最后一个人的编号
    public static int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        return (lastRemaining(n - 1, m) + m) % n;
    }

    // 0 1 2 3 4 5 n = 6 m = 3
    // 此时3变成了0，映射为0
    // 0 1 3 4 5
    // 3 4 0 1 2
}
