package bytedance.字符串;

import java.util.ArrayList;
import java.util.List;

public class Z字形变换 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<List<Character>> convertedStr = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            convertedStr.add(new ArrayList<>());
        }
        int curRow = 0;
        int curColumn = 0;
        // true -> 从上到下, false -> 从左到右
        boolean down = true;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (down) {
                convertedStr.get(curRow).add(ch);
                if (curRow == numRows - 1) {
                    down = false;
                    curRow--;
                    curColumn++;
                } else {
                    curRow++;
                }
            } else {
                convertedStr.get(curRow).add(ch);
                if (curRow == 0) {
                    down = true;
                    curRow++;
                } else {
                    curRow--;
                    curColumn++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < convertedStr.size(); i++) {
            for (int j = 0; j < convertedStr.get(i).size(); j++) {
                sb.append(convertedStr.get(i).get(j));
            }
        }
        return sb.toString();
    }
}
