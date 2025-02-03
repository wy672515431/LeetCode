package LeetCode_418;

public class A {
    public int maxGoodNumber(int[] nums) {
        int ans = 0;
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toBinaryString(a));
        sb.append(Integer.toBinaryString(b));
        sb.append(Integer.toBinaryString(c));
        ans = Math.max(ans, Integer.parseInt(sb.toString(), 2));
        sb.setLength(0);
        sb.append(Integer.toBinaryString(a));
        sb.append(Integer.toBinaryString(c));
        sb.append(Integer.toBinaryString(b));
        ans = Math.max(ans, Integer.parseInt(sb.toString(), 2));
        sb.setLength(0);
        sb.append(Integer.toBinaryString(b));
        sb.append(Integer.toBinaryString(a));
        sb.append(Integer.toBinaryString(c));
        ans = Math.max(ans, Integer.parseInt(sb.toString(), 2));
        sb.setLength(0);
        sb.append(Integer.toBinaryString(b));
        sb.append(Integer.toBinaryString(c));
        sb.append(Integer.toBinaryString(a));
        ans = Math.max(ans, Integer.parseInt(sb.toString(), 2));
        sb.setLength(0);
        sb.append(Integer.toBinaryString(c));
        sb.append(Integer.toBinaryString(a));
        sb.append(Integer.toBinaryString(b));
        ans = Math.max(ans, Integer.parseInt(sb.toString(), 2));
        sb.setLength(0);
        sb.append(Integer.toBinaryString(c));
        sb.append(Integer.toBinaryString(b));
        sb.append(Integer.toBinaryString(a));
        ans = Math.max(ans, Integer.parseInt(sb.toString(), 2));
        return ans;
    }
}
