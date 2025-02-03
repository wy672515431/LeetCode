package bytedance.数组;

public class 寻找重复数 {
    /**
     * 包含n + 1个数的数组，数字范围都在[1,n]存在一个重复的数
     * 我们对nums数组建图，每个位置i连接nums[i]的边，由于存在一个重复的数，则target这个位置有两条入边
     * 因此这个图是存在环的，且环的入口就是target
     * 我们通过快慢指针来进行寻找
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
