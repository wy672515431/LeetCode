package bytedance;

import java.util.ArrayList;
import java.util.List;

public class 文本左右对齐 {
    /**
     * 每行恰有maxWidth个字符，且左右两端对齐
     * 尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充
     * 尽可能均匀分配单词间的空格数量
     * 如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数
     * 文本的最后一行应为左对齐，且单词之间不插入额外的空格
     * @param words
     * @param maxWidth
     * @return
     */
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> fullJustify(String[] words, int maxWidth) {
        int start = 0, end = 0, n = words.length;
        int width = 0;
        while (end < n) {
            width += words[end].length();
            // 如果当前的宽度大于maxWidth
            if (width > maxWidth) {
                solve(words, start, end - 1, maxWidth);
                width = 0;
                start = end;
                continue;
            } else {
                width += 1;
                end += 1;
            }
        }
        solve(words, start, end - 1, maxWidth);
        return ans;
    }

    private void solve(String[] words, int start, int end, int maxWidth) {
        if (end == words.length - 1) {
            processLastLine(words, start, end, maxWidth);
        } else {
            process(words, start, end, maxWidth);
        }
    }

    private void process(String[] words, int start, int end, int maxWidth) {
        int len = 0;
        for (int i = start; i <= end; i++) {
            len += words[i].length();
        }
        int leftLen = maxWidth - len;
        if (start == end) {
            sb.append(words[start]);
            sb.repeat(" ", leftLen);
            ans.add(sb.toString());
            sb.setLength(0);
            return;
        }
        // 将leftLen分配到 end - start个空位
        int basic = leftLen / (end - start);
        int additional = leftLen % (end - start);
        for (int i = start; i <= end; i++) {
            sb.append(words[i]);
            // 最后一个字符不加
            if (i != end) {
                sb.repeat(" ", basic + (additional > 0 ? 1 : 0));
            }
            if (additional > 0) {
                additional -= 1;
            }
        }
        ans.add(sb.toString());
        sb.setLength(0);
    }

    private void processLastLine(String[] words, int start, int end, int maxWidth) {
        int len = 0;
        for (int i = start; i <= end; i++) {
            len += words[i].length();
        }
        int leftLen = maxWidth - len;
        for (int i = start; i <= end; i++) {
            // 左对齐
            sb.append(words[i]);
            if (i != end) {
                sb.repeat(" ", 1);
                leftLen--;
            }
        }
        sb.repeat(" ", leftLen);
        ans.add(sb.toString());
        sb.setLength(0);
    }
}
