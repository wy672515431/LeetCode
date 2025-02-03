package CF.CF_974_DIV3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class D {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            String[] input = bf.readLine().split(" ");
            // number of total days
            int n = Integer.parseInt(input[0]);
            // duration of the visit
            int d = Integer.parseInt(input[1]);
            // number of jobs
            int k = Integer.parseInt(input[2]);
            int[] ss = new int[n + 1];
            int[] es = new int[n + 1];
            for (int i = 0; i < k; i++) {
                input = bf.readLine().split(" ");
                int l = Integer.parseInt(input[0]);
                int r = Integer.parseInt(input[1]);
                ss[l]++;
                es[r]++;
            }
            // prefix
            // ss[i + 1]第i天已经开始的工作(不管结束
            // es[i + 1]第i天已经结束的工作
            for (int i = 0; i < n; i++) {
                ss[i + 1] += ss[i];
                es[i + 1] += es[i];
            }
            int maxJobs = 0;
            int ans1 = 0;
            int minJobs = k + 1;
            int ans2 = 0;
            for (int i = 1; i + d - 1 <= n; i++) {
                // [i, i + d - 1]正在进行的工作
                int jobs = ss[i + d - 1] - es[i - 1];
                if (jobs > maxJobs) {
                    maxJobs = jobs;
                    ans1 = i;
                }
                if (jobs < minJobs) {
                    minJobs = jobs;
                    ans2 = i;
                }
            }
            System.out.println(ans1 + " " + ans2);
        }
        bf.close();
    }
}
