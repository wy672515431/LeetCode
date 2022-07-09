package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class test {
    public static void main(String[] args) {
        char ch = 0;
        char ch1 = 0;
        System.out.println(ch == ch1);
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        //我们必须保证每个位置能够达到的情况下，选择最大加油量的加油站加油。
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> (b - a));
        int minRefuelStops = 0, preLocation = 0, nextLocation = 0, fuel = startFuel;
        for (int i = 0; i <= stations.length; i++) {
            nextLocation = (i < stations.length) ? stations[i][0] : target;
            fuel -= (nextLocation - preLocation);
            //加油
            while (fuel < 0 && !queue.isEmpty()) {
                fuel += queue.poll();
                minRefuelStops++;
            }
            if (fuel < 0) {
                return -1;
            }
            if (i < stations.length) {
                queue.offer(stations[i][1]);
                preLocation = nextLocation;
            }
        }
        return minRefuelStops;
    }


    
}


