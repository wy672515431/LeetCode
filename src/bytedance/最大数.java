package bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 最大数 {
    public String largestNumber(int[] nums) {
        List<String> strs = new ArrayList<>(Arrays.stream(nums).boxed().map(String::valueOf).toList());
        strs.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        StringBuilder sb = new StringBuilder();
        strs.forEach(sb::append);
        // 去除后面为0
        if (sb.charAt(0) == '0') {
            sb.setLength(1);
        }
        return sb.toString();
    }
}
