package bytedance.数组;

public class 交易逆序对的总数 {
    int ans = 0;
    public int reversePairs(int[] record) {
        // 归并排序
        mergeSort(record, 0, record.length - 1);
        return ans;
    }
    
    public void mergeSort(int[] record, int p, int q) {
        if (p < q) {
            int r = (q - p) / 2 + p;
            mergeSort(record, p, r);
            mergeSort(record, r + 1, q);
            merge(record, p, r, q);
        }
    }

    /**
     * 有序数组的两部分
     * @param record
     * @param p
     * @param r
     * @param q
     */
    private void merge(int[] record, int p, int r, int q) {
        int[] tem1 = new int[r - p + 1];
        int[] tem2 = new int[q - r];

        for (int i = p; i <= r; i++) {
            tem1[i - p] = record[i];
        }
        for (int i = r + 1; i <= q; i++) {
            tem2[i - r - 1] = record[i];
        }

        int i = 0, j = 0;
        for (int k = p; k <= q; k++) {
            // 可能已经到了数组末尾
            if (i == r - p + 1) {
                ans += (r - p + 1) - i;
                record[k] = tem2[j];
                j++;
                continue;
            }
            // 可能已经到了数组末尾
            if (j == q - r) {
                record[k] = tem1[i];
                i++;
                continue;
            }
            // 前面的
            if (tem1[i] <= tem2[j]) {
                record[k] = tem1[i];
                i++;
            } else {
                // tem1[i] > tem2[j]
                // 此时存在 length - i 个逆序对。i以及i以后的元素均大于tem2[j]
                ans += (r - p + 1) - i;
                record[k] = tem2[j];
                j++;
            }
        }
    }
}
