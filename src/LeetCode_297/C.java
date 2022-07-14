package LeetCode_297;

public class C {
    // //分成k份，每一份尽可能地均匀
    // //cookies地长度很小，可以考虑递归
    // int[] sum;
    // boolean[] isVisit;
    // int ans  = Integer.MAX_VALUE;
    // public int distributeCookies(int[] cookies, int k) {
    //     sum = new int[k];
    //     isVisit = new boolean[cookies.length];
    //     dfs(cookies, 0, k, 0);
    //     return ans;
    // }

    
    // public void dfs(int[] cookies, int cur, int k, int curCookie) {
    //     boolean ok = true;
    //     for (int i = 0; i < cookies.length; i++) {
    //         if (!isVisit[i]) {
    //             ok = false;
    //             break;
    //         }
    //     }
    //     if (ok) {
    //         int tem = 0;
    //         for (int i = 0; i < k; i++) {
    //             tem = Math.max(tem, sum[i]);
    //         }
    //         ans = Math.min(ans, tem);
    //         return;
    //     }
    //     if (!isVisit[curCookie]) {
    //         sum[cur] += cookies[curCookie];
    //         isVisit[curCookie] = true;
    //         for (int j = 0; j < k; j++) {
    //             dfs(cookies, j, k, curCookie + 1);
    //         }
    //         sum[cur] -= cookies[curCookie];
    //         isVisit[curCookie] = false;
    //     }
    // }
    int ans = Integer.MAX_VALUE;
    int[] cookies;
    int n;
    int k;
    
    public int distributeCookies(int[] cookies, int k) {
        
        //技巧：先发饼干较多的包，这样让回溯过程更快。下面的回溯代码是从最后一个饼干包开始发所以这里是从小到大排序
        Arrays.sort(cookies);
        
        this.cookies = cookies;
        n = cookies.length;
        this.k = k;
        
        //启动回溯
        backtrack(new int[k], n-1);
        
        return ans;
    }
    
    //bucket数组存放k个小朋友每个人当前的饼干数量，start为下一个要分发的饼干包下标
    public void backtrack(int[] bucket, int start){
        
        //饼干发完了，统计哪个小朋友获得的饼干最多，更新答案。
        if (start < 0){
            int curAns = Integer.MIN_VALUE;
            for (int count : bucket){
                curAns = Math.max(curAns, count);
            }
            ans = Math.min(ans, curAns);
            return;
        }
        
        //剪枝1：如果剩余的饼干包不够空手的小朋友分了，直接返回。
        int zeroCount = 0;
        for (int count : bucket){
            if (count == 0) zeroCount++;
        }
        if (zeroCount > start + 1) return;
        
        //剪枝2：如果某位小朋友的饼干数量比当前的答案还多，显然继续回溯下去也无法成为最优答案，直接返回。
        for (int i = 0; i < k; i++){
            if (bucket[i] > ans) return;
        }
        
        for (int i = 0; i < k; i++){
            //剪枝3：第一个零食包不管给哪个小朋友，所开启的回溯树都一样，只要给一个小朋友就行了，这样的回溯树一下子就少了很多。
            if (start == n - 1 && i > 0) return;
            
            //标准回溯代码
            bucket[i] += cookies[start];
            backtrack(bucket, start - 1);
            bucket[i] -= cookies[start];
        }
        return;
    }
}
