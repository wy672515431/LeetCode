package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class 最长连续序列 {
    //检查要合并的元素是否存在于数组中;
    Set<Integer> set = new HashSet<>();

    class UnionFindSet {
        //记录当前节点的父节点
        private final HashMap<Integer, Integer> fatherMap;
        //记录当前集合的大小
        private final HashMap<Integer, Integer> sizeMap;
        //记录所有集合中最大的集合
        private int maxNums = 1;

        public UnionFindSet() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void init(int[] nums) {
            for (int num : nums) {
                //初始化时节点设为我自己
                fatherMap.put(num, num);
                sizeMap.put(num, 1);
            }
        }

        public Integer findRepresentNode(Integer node) {
            Integer father = fatherMap.get(node);
            if (!Objects.equals(father, node)) {
                father = findRepresentNode(father);
            }
            //路径压缩
            fatherMap.put(node, father);
            return father;
        }

        public void union(Integer node1, Integer node2) {
            //要合并的两个元素中,有一个不存在
            if (!set.contains(node1) || !set.contains(node2)) {
                return;
            }
            //二者存在于不相同的集合内
            int representNode1 = findRepresentNode(node1);
            int representNode2 = findRepresentNode(node2);
            if (representNode1 != representNode2) {
                int size1 = sizeMap.get(representNode1);
                int size2 = sizeMap.get(representNode2);
                if (size1 > size2) {
                    fatherMap.put(representNode2, representNode1);
                    sizeMap.put(representNode1, size1 + size2);
                    maxNums = Math.max(maxNums, size1 + size2);
                } else {
                    fatherMap.put(representNode1, representNode2);
                    sizeMap.put(representNode2, size1 + size2);
                    maxNums = Math.max(maxNums, size1 + size2);
                }
            }
        }
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        Set<Integer> set1 = new HashSet();
        for (int i = 0; i < nums.length; i++)
            set1.add(nums[i]);
        int ans = 1;
        for (int i = 0; i < nums.length; i++) {
            if (set1.contains(nums[i] - 1))
                continue;
            int start = nums[i];
            while (set1.contains(start + 1))
                start++;
            ans = Math.max(ans, start - nums[i] + 1);
        }
        return ans;
    }
}
