package LeetCode_80_Double;

public class D {
    //我们寻找以nums[i]为开头的数组数量
    public static long countSubarrays(int[] nums, long k) {
        long ans = 0;
        int left = 0;
        int right = -1;
        int sum = 0;
        long score;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            right++;
            score = (long) sum * (right - left + 1);
            //右边界加1
            if (score < k) {
            } else {
                ans += right - left;;
                int originLeft = left;
                while (score >= k) {
                    if (left != originLeft) {
                        ans += right - left;;
                    }
                    sum -= nums[left];
                    left++;
                    score = (long) sum * (right - left + 1);
                }
            }
        }
        while (left < nums.length) {
            score = (long) sum * (right - left + 1);
            if (score >= k) {
                ans += right - left;
                int originLeft = left;
                while (score >= k) {
                    if (left != originLeft) {
                        ans += right - left + 1;
                    }
                    sum -= nums[left];
                    left++;
                    score = (long) sum * (right - left + 1);
                }
            } else {
                ans += right - left + 1;
                left++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5,2,6,8,9,7};
        System.out.println(countSubarrays(nums, 50));
        countSubarrays(nums, 50);
    }
}
