package LeetCode;

import java.util.*;

/**
 * LeetCode 1606
 */
public class FindServers {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        //记录繁忙的次数
        int[] cnt = new int[k];
        List<Integer> ans = new ArrayList<>();
        TreeSet<Integer> freeServer = new TreeSet<>();
        PriorityQueue<BusyServer> busyServer = new PriorityQueue<>(new Comparator<BusyServer>() {
            @Override
            public int compare(BusyServer o1, BusyServer o2) {
                return o1.endTime - o2.endTime;
            }
        });
        //初始化，开始所有的服务器都处于空闲状态
        for (int i = 0; i < k; i++) {
            freeServer.add(i);
        }
        for (int i = 0; i < arrival.length; i++) {
            while (!busyServer.isEmpty()) {
                if (busyServer.peek().endTime <= arrival[i]) {
                    BusyServer tem = busyServer.poll();
                    freeServer.add(tem.id);
                } else {
                    break;
                }
            }
            //如果不存在空闲状态的服务求，直接忽略
            if (freeServer.size() == 0) {
                continue;
            } else {
                int index;
                if (freeServer.contains(i % k)) {
                    index = i % k;
                } else {
                    if (freeServer.higher(i % k) != null) {
                        index = freeServer.higher(i % k);
                    } else {
                        index = freeServer.first();
                    }
                }
                //次数增加
                cnt[index]++;
                freeServer.remove(index);
                busyServer.offer(new BusyServer(index, arrival[i] + load[i]));
            }

        }

        int max = 0;
        for (int i = 0; i < k; i++) {
            if (cnt[i] > max) {
                max = Math.max(max, cnt[i]);
                ans.clear();
                ans.add(i);
            } else if (cnt[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }

    class BusyServer {
        int id;
        int endTime;

        BusyServer(int id, int endTime) {
            this.id = id;
            this.endTime = endTime;
        }
    }


    public static void main(String[] args) {
        int k = 3;
        int[] arrival = {1, 2, 3, 4, 5};
        int[] load = {5, 2, 3, 3, 3};
        FindServers tem = new FindServers();
        tem.busiestServers(k, arrival, load);
    }
}
