package LeetCode_356;

import java.util.Arrays;
import java.util.Comparator;

public class C {
    public String minimumString(String a, String b, String c) {
        String[] candidates = new String[6];
        candidates[0] = solve(a, b, c);
        candidates[1] = solve(a, c, b);
        candidates[2] = solve(b, a, c);
        candidates[3] = solve(b, c, a);
        candidates[4] = solve(c, a, b);
        candidates[5] = solve(c, b, a);
        Arrays.sort(candidates, (o1, o2) -> {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() < o2.length()) {
                return -1;
            } else {
                return o1.compareTo(o2);
            }
        });
        return candidates[0];
    }

    private static String solve(String a, String b, String c) {
        // we need to find that if b is substr of a
        if (a.contains(b) && a.contains(c)) {
            return a;
        } else if (a.contains(b)) {
            return solve2(a, c);
        } else if (a.contains(c)) {
            return solve2(a, b);
        } else if (b.contains(c)) {
            return solve2(a, b);
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i1 = a.length() - 1;
        int i2 = 0;
        int len = 0;
        while (i1 >= 0 && i2 < b.length()) {
            sb1.append(a.charAt(i1));
            sb2.append(b.charAt(i2));
            StringBuilder temp = new StringBuilder(sb1);
            if (temp.reverse().toString().contentEquals(sb2)) {
                len = i2 + 1;
            }
            i1--;
            i2++;
        }
        i1 = a.length() - 1 - len;
        i2 = len;
        sb1.setLength(0);
        sb2.setLength(0);
        sb1.append(a.substring(i1 + 1));
        for (int i = 0; i <= i1; i++) {
            sb.append(a.charAt(i));
        }
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        int i3 = b.length() - 1;
        int i4 = 0;
        int len1 = 0;
        while (i3 >= 0 && i4 < c.length()) {
            sb3.append(b.charAt(i3));
            sb4.append(c.charAt(i4));
            StringBuilder temp = new StringBuilder(sb3);
            if (temp.reverse().toString().contentEquals(sb4)) {
                len1 = i4 + 1;
            }
            i3--;
            i4++;
        }
        i3 = b.length() - 1 - len1;
        i4 = len1;
        sb3.setLength(0);
        sb4.setLength(0);
        sb3.append(b.substring(i3 + 1));
        // overlap
        if (i2 >= i3) {
            sb.append(b);
        } else {
            sb.append(sb1);
            for (int i = i2; i <= i3; i++) {
                sb.append(b.charAt(i));
            }
            sb.append(sb3);
        }
        for (int i = i4; i < c.length(); i++) {
            sb.append(c.charAt(i));
        }
        return sb.toString();
    }

    private static String solve2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i1 = a.length() - 1;
        int i2 = 0;
        int len = 0;
        while (i1 >= 0 && i2 < b.length()) {
            sb1.append(a.charAt(i1));
            sb2.append(b.charAt(i2));
            StringBuilder temp = new StringBuilder(sb1);
            if (temp.reverse().toString().contentEquals(sb2)) {
                len = i2 + 1;
            }
            i1--;
            i2++;
        }
        i1 = a.length() - 1 - len;
        i2 = len;
        sb1.setLength(0);
        sb2.setLength(0);
        sb1.append(a.substring(i1 + 1));
        for (int i = 0; i <= i1; i++) {
            sb.append(a.charAt(i));
        }
        sb.append(sb1);
        for (int i = i2; i < b.length(); i++) {
            sb.append(b.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solve("bbc", "b", "bcb"));
    }
}
