package LeetCode.BinarySearch;

public class 有序数组中的单一元素 {
    //二分查找，我们不妨假设只出现一次的元素下标为x
    //下标x左边和右边的元素个数为偶数个
    //对于x下标的左边下标y，nums[y] = nums[y + 1]则y是偶数
    //对于x下标的右边下标z, nums[z] = nums[z + 1]则z是奇数
    //x是相同元素开始下标的奇偶分界
    //如果mid是偶数，则比较nums[mid]和nums[mid + 1]是否相等
    //如果mid是奇数，则比较nums[mid - 1]和nums[mid]
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        int mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            //mid为奇数则减1，mid为偶数则加1
            //此时x在mid左边
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
