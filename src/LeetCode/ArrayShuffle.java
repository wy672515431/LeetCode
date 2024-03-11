package LeetCode;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class ArrayShuffle {
    int[] source;
    int[] nums;
    public ArrayShuffle(int[] nums) {
        this.source = nums;
        this.nums = new int[nums.length];
        System.arraycopy(nums, 0, this.nums, 0, nums.length);
    }

    public int[] reset() {
        return this.source;
    }

    /**
     * shuffle algorithm
     * @return shuffled array
     */
    public int[] shuffle() {
        Random random = new Random();
        int size = this.nums.length;
        for (int i = size; i >= 1; i--) {
            swap(this.nums, i - 1, random.nextInt(i));
        }
        return this.nums;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
