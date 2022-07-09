package LeetCode;

public class 下一个更大元素 {
    public int nextGreaterElement(int n) {
        char[] sequence = String.valueOf(n).toCharArray();
        //下一个更大元素
        //1 5 8 3 4 6 5
        //8 5 1 3 4 2 1
        //从头开始遍历sequence,若sequence降序排列，则返回-1.
        //否则我们应该找到一位满足后面存在比他大的数
        int reverseStartIndex = -1;
        int reverseEndIndex = -1;
        for (int i = 0; i < sequence.length - 1; i++) {
            if (sequence[i] >= sequence[i + 1]) {
                if (reverseStartIndex != -1 && sequence[i + 1] > sequence[reverseStartIndex]) {
                    reverseEndIndex = i + 1;
                }
            } else {
                reverseStartIndex = i;
                reverseEndIndex = i + 1;
            }
        }
        if (reverseStartIndex != -1) {
            char tem = sequence[reverseStartIndex];
            sequence[reverseStartIndex] = sequence[reverseEndIndex];
            sequence[reverseEndIndex] = tem;
        } else {
            return -1;
        }
        reverseCharSequence(sequence, reverseStartIndex + 1, sequence.length - 1);
        try {
            int ans = Integer.parseInt(new String(sequence), 0, sequence.length, 10);
            return ans;
        } catch (Exception e) {
            return -1;
        }
    }

    private void reverseCharSequence(char[] sequence, int start, int end) {
        while (start < end) {
            char tem = sequence[start];
            sequence[start] = sequence[end];
            sequence[end] = tem;
            start++;
            end--;
        }
    }
}
