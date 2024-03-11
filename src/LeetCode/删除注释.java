package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class 删除注释 {
    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        // "//" or "/* */"
        boolean inBlockComment = false;
        StringBuilder sb = new StringBuilder();
        for (String line : source) {
            int len = line.length();
            int i = 0;
            while (i < len) {
                char ch = line.charAt(i);
                // 是否在块注释中
                if (inBlockComment) {
                    // the end of the block comment
                    if (ch == '*' && i + 1 < len) {
                        char nextCh = line.charAt(i + 1);
                        if (nextCh == '/') {
                            inBlockComment = false;
                            i += 2;
                        } else {
                            i += 1;
                        }
                    } else {
                        i += 1;
                    }
                    // 如果不是"*/",则直接跳过
                } else {
                    if (ch == '/' && i + 1 < len) {
                        char nextCh = line.charAt(i + 1);
                        if (nextCh == '/') {
                            // line comment
                            if (sb.length() != 0) {
                                ans.add(sb.toString());
                            }
                            sb.setLength(0);
                            break;
                        } else if (nextCh == '*') {
                            // block comment
                            inBlockComment = true;
                            i += 2;
                            continue;
                        }
                    }
                    sb.append(ch);
                    i += 1;
                }
            }
            // reach the end of line
            if (i == len && !inBlockComment) {
                if (sb.length() != 0) {
                    ans.add(sb.toString());
                }
                sb.setLength(0);
            }
        }
        return ans;
    }
}
