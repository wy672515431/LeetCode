package LeetCode_411;

public class C {
    public String largestPalindrome(int n, int k) {
        StringBuilder sb = new StringBuilder();
        if (k == 1 || k == 3 || k == 9) {
            sb.repeat('9', n);
            return sb.toString();
        } else if (k == 2) {
            // 偶数
            for (int i = 0; i < n; i++) {
                if (i == 0 || i == n - 1) {
                    sb.append('8');
                } else {
                    sb.append('9');
                }
            }
            return sb.toString();
        } else if (k == 4) {
            // 最后两位数能被4整除，找到最大的
            if (n <= 4) {
                sb.repeat('8', n);
                return sb.toString();
            } else {
                for (int i = 0; i < n; i++) {
                    if (i == 0 || i == 1 || i == n - 2 || i == n - 1) {
                        sb.append('8');
                    } else {
                        sb.append('9');
                    }
                }
                return sb.toString();
            }
        } else if (k == 5) {
            // 5的倍数，最后一位必须是5
            for (int i = 0; i < n; i++) {
                if (i == 0 || i == n - 1) {
                    sb.append('5');
                } else {
                    sb.append('9');
                }
            }
            return sb.toString();
        } else if (k == 6) {
            // 能被3整数，也能被2整除
            if (n <= 2) {
                sb.repeat('6', n);
                return sb.toString();
            } else if (n % 2 == 1) {
                for (int i = 0; i < n; i++) {
                    if (i == 0 || i == n - 1 || i == n / 2) {
                        sb.append('8');
                    } else {
                        sb.append('9');
                    }
                }
                return sb.toString();
            } else {
                for (int i = 0; i < n; i++) {
                    if (i == 0 || i == n - 1) {
                        sb.append('8');
                    } else if (i == n / 2 || i == n / 2 - 1) {
                        sb.append('7');
                    } else {
                        sb.append('9');
                    }
                }
                return sb.toString();
            }
        } else if (k == 7) {
            if (n % 6 == 0) {
                sb.repeat('9', n);
            } else if (n % 12 == 1) {
                sb.repeat('9', n / 2);
                sb.append('7');
                sb.repeat('9', n / 2);
            } else if (n % 12 == 2) {
                sb.repeat('9', n / 2 - 1);
                sb.repeat('7', 2);
                sb.repeat('9', n / 2 - 1);
            } else if (n % 12 == 3) {
                sb.repeat('9', n / 2 - 1);
                sb.append("959");
                sb.repeat('9', n / 2 - 1);
            } else if (n % 12 == 4) {
                sb.repeat('9', n / 2 - 2);
                sb.append("9779");
                sb.repeat('9', n / 2 - 2);
            } else if (n % 12 == 5) {
                sb.repeat('9', n / 2 - 2);
                sb.append("99799");
                sb.repeat('9', n / 2 - 2);
            } else if (n % 12 == 7) {
                sb.repeat('9', n / 2 - 3);
                sb.append("9994999");
                sb.repeat('9', n / 2 - 3);
            } else if (n % 12 == 8) {
                sb.repeat('9', n / 2 - 4);
                sb.append("99944999");
                sb.repeat('9', n / 2 - 4);
            } else if (n % 12 == 9) {
                sb.repeat('9', n / 2 - 4);
                sb.append("999969999");
                sb.repeat('9', n / 2 - 4);
            } else if (n % 12 == 10) {
                sb.repeat('9', n / 2 - 5);
                sb.append("9999449999");
                sb.repeat('9', n / 2 - 5);
            } else if (n % 12 == 11) {
                sb.repeat('9', n / 2 - 5);
                sb.append("99999499999");
                sb.repeat('9', n / 2 - 5);
            }
            return sb.toString();
        } else if (k == 8) {
            if (n <= 6) {
                sb.repeat('8', n);
                return sb.toString();
            } else {
                // 1000能被8整除
                sb.repeat('8', 3);
                sb.repeat('9', n - 6);
                sb.repeat('8', 3);
                return sb.toString();
            }
        }
        return sb.toString();
    }
}
