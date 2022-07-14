package LeetCode_301;

public class C {
    //start中最右边的L和target中最右边的L对应
    //start中最左边的R和target中最左边的R对应
    //--R--
    /*
    "_LL__R__R_"
    "L___L___RR"
    "_L__R__R_L"
    "L______RR_"
     */
    public boolean canChange(String start, String target) {
        int n = start.length();
        int countLOfStart = 0;
        int countROfStart = 0;
        int countLOfTarget = 0;
        int countROfTarget = 0;
        for (int i = 0; i < n; i++) {
            char ch = start.charAt(i);
            char targetCh = target.charAt(i);
            //对于L，countLOfStart  <= countLofTarget合格
            //同时前面R的个数必须严格相等
            if (ch == 'L') {
                if (countLOfStart > countLOfTarget) {
                    return false;
                }
                if (countROfStart != countROfTarget) {
                    return false;
                }
            } else if (ch == 'R') {
                if (countROfStart < countROfTarget) {
                    return false;
                }
                if (countLOfStart != countLOfTarget) {
                    return false;
                }
            } else {
                if (countLOfStart > countLOfTarget) {
                    return false;
                }
                if (countROfStart < countROfTarget) {
                    return false;
                }
            }
            if (ch == 'L') {
                countLOfStart++;
            }
            if (ch == 'R') {
                countROfStart++;
            }
            if (targetCh == 'L') {
                countLOfTarget++;
            }
            if (targetCh == 'R') {
                countROfTarget++;
            }
        }
        if (countLOfStart != countLOfTarget || countROfStart != countROfTarget) {
            return false;
        }
        return true;
    }
}
