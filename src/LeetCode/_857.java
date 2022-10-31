package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _857 {
    /**
     * 雇佣k名工人的最低成本
     * 我们需满足支付的工资最低为wage[i]
     * 要满足 A工资 ： B工资 = A工作质量 : B工作质量
     * A工资 / A工作质量 = B工资 / B工作质量
     * 在确定A实际工资的情况下， B工资 = A工资 / A工作质量 * B工作质量
     * @param quality
     * @param wage
     * @param k
     * @return
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] h = new Integer[n];
        for (int i = 0; i < n; i++) {
            h[i] = i;
        }
        //我们不妨假设已经选择了一个工资组，整个工资组的总工作质量为totalq,总工资为totalc
        //则对于每个工人来说，需满足 totalc * (quality[i]) / totalq >= wage[i]
        //totalc =  totalq * (wage[i] / quality[i])
        //则totalc与max(wage[i] / quality[i])有关
        //wage[a] / quality[a] - wage[b] / quality[b] 从小到大排序
        //我们不妨假设x为工资组权重最大的一个，则剩下的我们只需选权重小于x，且工作质量小的即可。
        Arrays.sort(h, (a, b) -> {
            return quality[b] * wage[a] - quality[a] * wage[b];
        });
        double res = 1e9;
        double totalq = 0.0;
        //大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int i = 0; i < k - 1; i++) {
            //k - 1个总工作时间
            totalq += quality[h[i]];
            pq.offer(quality[h[i]]);
        }
        //从权重第k大的进行遍历
        for (int i = k - 1; i < n; i++) {
            int idx = h[i];
            totalq += quality[idx];
            pq.offer(quality[idx]);
            double totalc = ((double) wage[idx] / quality[idx]) * totalq;
            res = Math.min(res, totalc);
            //维持k - 1个
            totalq -= pq.poll();
        }
        return res;
    }
}
