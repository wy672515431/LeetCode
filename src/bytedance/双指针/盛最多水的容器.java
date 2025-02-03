package bytedance.双指针;

public class 盛最多水的容器 {
    public int maxArea(int[] height) {
        // 固定高度，找到宽度
        int n = height.length;
        int ans = 0;
        int i = 0, j = n - 1;
        while (i < j) {
            // 当前的矩形面积
            ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));
            // 针对较低的垂线，我们发现继续移动j不会增大矩形的面积
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }
}
