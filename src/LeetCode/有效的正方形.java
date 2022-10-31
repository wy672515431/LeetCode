package LeetCode;

import java.util.Arrays;

public class 有效的正方形 {
    private static final double eps = 1e-9;
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        double dis1 = distance(p1, p2);
        double dis2 = distance(p1, p3);
        double dis3 = distance(p2, p3);
        double dis4 = distance(p3, p4);
        double dis5 = distance(p1, p4);
        double dis6 = distance(p2, p4);
        double[] distances = new double[6];
        distances[0] = dis1;
        distances[1] = dis2;
        distances[2] = dis3;
        distances[3] = dis4;
        distances[4] = dis5;
        distances[5] = dis6;

        Arrays.sort(distances);
        if (distances[0] == distances[3] && distances[4] == distances[5] && distances[4] > distances[3]) {
            return true;
        } 
        return false;

    }

    public double distance(int[] p1, int[] p2) {
        return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + 
        (p1[1] - p2[1]) * (p1[1] - p2[1]));
    }
}
