package bytedance.二分;

public class 等差数列中缺失的数字 {
    public int missingNumber(int[] arr) {
        int len = arr.length;
        int low = 0;
        int high = len - 1;
        // 可以求导公差
        int diff = (arr[high] - arr[low]) / len;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            // 前面没有问题
            if (arr[mid] == arr[low] + (mid - low) * diff) {
                // 判断是否是后面一个缺失
                if (mid < len - 1 && arr[mid + 1] != arr[mid] + diff) {
                    return arr[mid] + diff;
                }
                // 不是
                low = mid + 1;
            } else {
                // 前面有问题
                high = mid - 1;
            }
        }
        // 方差为0
        return arr[0];
    }
}
