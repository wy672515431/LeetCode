package LeetCode;

public class Sort {
    public static  final  int INF = 0x3f3f3f3f;
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

        for(int i = r + 1; i <= q; i ++) {
            tem2[i -r - 1] = arr[i];
        }
        tem2[q - r] = INF;

        int i = 0;
        int j = 0;
        for(int k = p; k <= q; k ++) {
            if(tem1[i] <= tem2[j]) {
                arr[k] = tem1[i];
                i++;
            }else{
                arr[k] = tem2[j];
                j++;
            }
        }
    }

    public void mergeSort(int[] arr, int p, int q) {
        if(p < q) {
            int r = (p + q) >> 1;
            mergeSort(arr, p, r);
            mergeSort(arr, r + 1, q);
            merge(arr, p, r, q);
        }
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] test = new int[] {2, 0, 2, 1, 1, 0};
//        sort.mergeSort(test, 0, test.length - 1);
        sort.quickSort(test, 0 , test.length - 1);
        for(int i = 0; i < test.length; i ++) {
            System.out.println(test[i]);
        }
    }
}
