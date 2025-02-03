package LeetCode_144_Double;

public class A {
    public boolean canAliceWin(int n) {
        int removeCount = 10;
        int surplus = n;
        boolean flag = true;
        while (true) {
            if (surplus < removeCount) {
                return !flag;
            }
            surplus -= removeCount;
            removeCount--;
            flag = !flag;
        }
    }
}
