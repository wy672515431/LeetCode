package LeetCode.BinarySearch;

public class 爱吃香蕉的珂珂 {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0x3f3f3f3f;
        int mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (check(piles, mid, h)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public boolean check(int[] piles, int speed, int h) {
        for (int pile : piles) {
            int hourPerPile = (pile % speed == 0) ? (pile / speed) : (pile / speed + 1);
            h -= hourPerPile;
        }
        return h >= 0;
    }
}
