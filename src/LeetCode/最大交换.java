package LeetCode;

public class 最大交换 {
    public int maximumSwap(int num) {
        char[] charSequence = String.valueOf(num).toCharArray();
        // 9 8 7 4 3 5 6->
        for (int i = 0; i < charSequence.length; i++) {
            char firstCh = charSequence[i];
            char maxChar = firstCh;
            int pos = i;
            for (int j = i + 1; j < charSequence.length; j++) {
                //尽可能选后面
                if (charSequence[j] > maxChar || (charSequence[j] == maxChar && maxChar != firstCh)) {
                    maxChar = charSequence[j];
                    pos = j;
                }
            }
            if (pos != i) {
                charSequence[i] = maxChar;
                charSequence[pos] = firstCh;
                break;
            }
        }
        return Integer.parseInt(new String(charSequence));
    }
}
