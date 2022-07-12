package LeetCode_82_Double;

import java.util.Arrays;
import java.util.HashSet;

public class B {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < passengers.length; i++) {
            set.add(passengers[i]);
        }
        int ans = 1;
        int curIndex =  -1;
        //buses每次可以承载的乘客人数为Math.min(capacity, 此时已经到达且没上车的乘客)
        for (int i = 0; i < buses.length; i++) {
            int numOfPassengers = upperBound(passengers, buses[i]) - curIndex - 1;
            int maxTime;
            if (numOfPassengers < capacity) {
                maxTime = buses[i];
                if (numOfPassengers == 0 || passengers[curIndex + numOfPassengers] != buses[i]) {
                    ans = maxTime;
                } else {
                    for (int j = curIndex + numOfPassengers; j > curIndex; j--) {
                        if (!set.contains(passengers[j] - 1)) {
                            ans = passengers[j] - 1;
                            break;
                        }
                    }
                }
            } else {
                for (int j = curIndex + capacity; j > curIndex; j--) {
                    if (!set.contains(passengers[j] - 1)) {
                        ans = passengers[j] - 1;
                        break;
                    }
                }
            }
            curIndex += Math.min(numOfPassengers, capacity);
        }
        return ans;
    }

    /**
     * 返回第一位大于busArriveTime乘客的编号
     * 如果不存在直接返回length + 1
     * @param passengers
     * @param time
     * @return
     */
    public int upperBound(int[] passengers, int time) {
        int low = 0;
        int high = passengers.length - 1;
        int mid;
        if (time >= passengers[high]) {
            return high + 1;
        }
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (passengers[mid] <= time) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
