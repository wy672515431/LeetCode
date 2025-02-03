package bytedance.二分;

/**
 * 某班级 n 位同学的学号为 0 ~ n-1。
 * 点名结果记录于升序数组 records。假定仅有一位同学缺席，请返回他的学号。
 */
public class 点名 {
    public int takeAttendance(int[] records) {
        int low = 0, high = records.length - 1;
        int ans = records.length;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (records[mid] == mid) {
                low = mid + 1;
            } else {
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }
}
