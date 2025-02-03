package bytedance.图;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class 最小基因变化 {
    public int minMutation(String start, String end, String[] bank) {
        int ans = -1;
        // k : gene, v: 从start到k的最小变化次数
        HashMap<String, Integer> mMap = new HashMap<>();
        // 记录已经访问过的基因
        HashSet<String> mSet  = new HashSet<>();
        for (int i = 0; i < bank.length; i++) {
            mMap.put(bank[i], -1);
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        mMap.put(start, 0);
        mSet.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            int curLen = mMap.get(cur);
            if (cur.equals(end)) {
                ans = curLen;
                break;
            }
            for (int i = 0; i < bank.length; i++) {
                if (mSet.contains(bank[i])) {
                    continue;
                }
                // 计算两个基因之间的差异
                int cnt = 0;
                for (int j = 0; j < cur.length(); j++) {
                    if (cur.charAt(j) != bank[i].charAt(j)) {
                        cnt++;
                    }
                }
                // 只有一个字符不同，才能看作是基因变化
                if (cnt == 1) {
                    mSet.add(bank[i]);
                    mMap.put(bank[i], curLen + 1);
                    queue.offer(bank[i]);
                }
            }
        }
        return ans;
    }
}
