package bytedance;

public class 数组中的第k个最大元素 {
    public int findKthLargest(int[] nums, int k) {
        // 堆排序，弹出k次，就是第k大元素，时间复杂度O(nlogn)
        // 1. 建堆 2.调整堆
        /*
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            // 弹出, 将最大值弹出到最后
            swap(nums, 0, i);
            heapSize--;
            adjustHeap(nums, 0, heapSize);
        }
        return nums[0];
        */

        // 通过快排查找
        // 快排会选择一个pivot，使左边的数都大于pivot，右边的数都小于pivot
        return partition(nums, 0, nums.length - 1, k);
    }

    private int partition(int[] nums, int start, int end, int k) {
        int pivot = nums[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && nums[j] < pivot) {
                j--;
            }
            // nums[j] >= pivot
            if (i < j) {
                // 此时nums[i]的值已经记录在pivot里面
                nums[i] = nums[j];
                i++;
            }
            while (i < j && nums[i] > pivot) {
                i++;
            }
            if (i < j) {
                // 此时j已经置换了
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = pivot;
        if (i + 1 == k) {
            return pivot;
        } else if (i + 1 < k) {
            return partition(nums, i + 1, end, k);
        } else {
            return partition(nums, start, i - 1, k);
        }
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        // 建堆，adjust堆
        // 从非叶子节点进行调整，下标从0开始，2 * i + 1 == heapSize - 1, 2 * i + 2 == heapSize - 1
        // i == heapSize / 2 - 1, 当2 * i + 2 == heapSize - 1时, heapSize为奇数，(heapSize - 1) / 2 == (heapSize) / 2
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, heapSize);
        }
    }

    private void adjustHeap(int[] nums, int i, int heapSize) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        // 子节点必须要小于父节点
        int largest = i;
        if (leftChild < heapSize && nums[leftChild] > nums[largest]) {
            largest = leftChild;
        }
        if (rightChild < heapSize && nums[rightChild] > nums[largest]) {
            largest = rightChild;
        }
        if (largest != i) {
            // 交换
            swap(nums, i, largest);
            // 交换后，其子节点也要再做一次
            adjustHeap(nums, largest, heapSize);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
