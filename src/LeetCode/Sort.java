package LeetCode;

public class Sort {
    public static final int INF = 0x3f3f3f3f;

    private int partition(int[] arr, int p, int q) {
        int pivot = arr[p];
        int i = p;
        int j = q;
        while (i < j) {
            while (i < j && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            while (i < j && arr[i] < pivot) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = pivot;
        return i;
    }

    public void quickSort(int[] arr, int p, int q) {
        int pivot;
        if (p < q) {
            pivot = partition(arr, p, q);
            quickSort(arr, p, pivot - 1);
            quickSort(arr, pivot + 1, q);
        }
    }


    private void merge(int[] arr, int p, int r, int q) {
        int length1 = r - p + 1;
        int length2 = q - r;

        int[] tem1 = new int[length1 + 1];
        int[] tem2 = new int[length2 + 1];

        for (int i = p; i <= r; i++) {
            tem1[i - p] = arr[i];
        }
        tem1[r - p + 1] = INF;

        for (int i = r + 1; i <= q; i++) {
            tem2[i - r - 1] = arr[i];
        }
        tem2[q - r] = INF;

        int i = 0;
        int j = 0;
        for (int k = p; k <= q; k++) {
            if (tem1[i] <= tem2[j]) {
                arr[k] = tem1[i];
                i++;
            } else {
                arr[k] = tem2[j];
                j++;
            }
        }
    }

    public void mergeSort(int[] arr, int p, int q) {
        if (p < q) {
            int r = (p + q) >> 1;
            mergeSort(arr, p, r);
            mergeSort(arr, r + 1, q);
            merge(arr, p, r, q);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        // 执行k - 1次弹出操作
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    // heap
    public void buildMaxHeap(int[] a, int heapSize) {
        // 建堆
        // 首先是将a构建为一个完全二叉树 a[i]的左节点是a[2 * i + 1]，右节点是a[2 * i + 2]
        // 我们知道最后一个非叶子节点的坐标应该 2 * i + 2 == heapSize || 2 * i + 1 == heapSize
        // i = heapSize - 1 / 2
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(a, i, heapSize);
        }
    }

    /**
     *
     * @param a
     * @param i the leaf node
     * @param heapSize
     */
    public void maxHeapify(int[] a, int i, int heapSize) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < heapSize && a[left] > a[largest]) {
            largest = left;
        }
        if (right < heapSize && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(a, i, largest);
            // 被换下去的也需要在进行一次调整
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] test = new int[]{2, 0, 2, 1, 1, 0};
//        sort.mergeSort(test, 0, test.length - 1);
        sort.quickSort(test, 0, test.length - 1);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
