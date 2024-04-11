package myTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Stream;

public class Base {
    static class Person {
        int age;
        Person(int age) {
            this.age = age;
        }
    }
    public static void main(String[] args) throws InterruptedException {
    }

    public String customSortString(String order, String s) {
        int val = 1;
        int[] value = new int[26];
        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            if (value[ch - 'a'] == 0) {
                value[ch - 'a'] = val;
                val++;
            }
        }
        Character[] chs = s.chars().mapToObj(ch -> (char) ch).toArray(Character[]::new);
        Arrays.sort(chs, Comparator.comparingInt(o -> value[o - 'a']));
        StringBuilder sb = new StringBuilder();
        for (char ch : chs) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, Comparator.comparingInt((int[]o) -> o[0]).reversed());
        int ans = 0;
        for (int[] box : boxTypes) {
            ans += box[1] * Math.min(box[0], truckSize);
            truckSize -= Math.min(box[0], truckSize);
            if (truckSize <= 0) {
                break;
            }
        }
        return ans;
    }
}