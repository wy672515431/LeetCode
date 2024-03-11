package LeetCode;


public class 形成目标数组的子数组最少增加次数 {
    /**
     * given an initial array. In one operation, I can choose any subarray and add 1 to all its elements.
     *
     * @param target the target array
     * @return Return the minimum number of operations needed to form target array from initial array.
     */
    public int minNumberOperations(int[] target) {
        //In one operation, I can choose any subarray and add 1 to all its elements.

        int res = target[0];
        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                res += target[i] - target[i - 1];
            }
        }
        return res;
    }
}
