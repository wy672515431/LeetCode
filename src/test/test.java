package test;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class test {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        //我们必须保证每个位置能够达到的情况下，选择最大加油量的加油站加油。
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (b - a));
        int minRefuelStops = 0, preLocation = 0, nextLocation = 0, fuel = startFuel;
        for (int i = 0; i <= stations.length; i++) {
            nextLocation = (i < stations.length) ? stations[i][0] : target;
            fuel -= (nextLocation - preLocation);
            //加油
            while (fuel < 0 && !queue.isEmpty()) {
                fuel += queue.poll();
                minRefuelStops++;
            }
            if (fuel < 0) {
                return -1;
            }
            if (i < stations.length) {
                queue.offer(stations[i][1]);
                preLocation = nextLocation;
            }
        }
        return minRefuelStops;
    }


    public int minOperations(String str) {
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        // init
        for (int i = 0; i < 26; i++) {
            set.add((char) ('a' + i));
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            set.remove(ch);
        }
        int ans = 0;
        int size = set.size();
        for (Integer val : map.values()) {
            if (size > 0 && val > 2) {
                int tem = Math.min(size, (val - 1) / 2);
                ans += tem;
                size -= tem;
                val -= 2 * tem;
            }
            ans += (val - 1);
        }
        return ans;
    }

    static ArrayList<Integer> check = new ArrayList<>();

    public static ArrayList<TreeNode> getBinaryTrees(ArrayList<Integer> preOrder, ArrayList<Integer> inOrder) {
        ArrayList<TreeNode> list = build(preOrder, 0, preOrder.size() - 1, inOrder, 0, inOrder.size() - 1);
        ArrayList<TreeNode> ans = new ArrayList<>();
        for (TreeNode treeNode : list) {
            check.clear();
            preorder(treeNode);
            if (check.size() == preOrder.size()) {
                ans.add(treeNode);
            }
        }
        return ans;
    }

    public static void preorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        check.add(treeNode.val);
        preorder(treeNode.left);
        preorder(treeNode.right);
    }

    public static ArrayList<TreeNode> build(ArrayList<Integer> preorder, int ps, int pe, ArrayList<Integer> inorder,
                                            int is,
                                            int ie) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        if (ps > pe || is > ie) {
            return ans;
        }
        // root - preOrder.get(ps)
        // find the root in inorder -  is <= rootPos <= ie
        // 中序左子树的范围 [is, rootPos - 1], 前序左子树的范围[ps + 1, ] len = rootPos - is
        // 右子树的范围 [rootPos + 1, ie]
        int rootVal = preorder.get(ps);
        for (int i = is; i <= ie; i++) {
            if (inorder.get(i) == rootVal) {
                ArrayList<TreeNode> leftNodeList = build(preorder, ps + 1, ps + i - is, inorder, is, i - 1);
                ArrayList<TreeNode> rightNodeList = build(preorder, ps + i - is + 1, pe, inorder, i + 1, ie);
                if (leftNodeList.size() == 0 && rightNodeList.size() == 0) {
                    TreeNode root = new TreeNode(rootVal);
                    ans.add(root);
                } else if (leftNodeList.size() == 0) {
                    for (TreeNode node : rightNodeList) {
                        TreeNode root = new TreeNode(rootVal);
                        root.right = node;
                        ans.add(root);
                    }
                } else if (rightNodeList.size() == 0) {
                    for (TreeNode node : leftNodeList) {
                        TreeNode root = new TreeNode(rootVal);
                        root.left = node;
                        ans.add(root);
                    }
                } else {
                    for (TreeNode treeNode : leftNodeList) {
                        for (TreeNode node : rightNodeList) {
                            TreeNode root = new TreeNode(rootVal);
                            root.left = treeNode;
                            root.right = node;
                            ans.add(root);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static int minOperations1(String str) {
        // write code here
        int len = str.length();
        int[] dp0 = new int[len];
        dp0[0] = str.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < len; i++) {
            char ch = str.charAt(i);
            if (i == 1) {
                dp0[i] = ch == '0' ? dp0[i - 1] : 1;
            } else {
                dp0[i] = ch == '0' ? dp0[i - 1] : dp0[i - 2] + 1;
            }
        }
        int[] dp1 = new int[len];
        dp1[0] = str.charAt(0) == '1' ? 0 : 1;
        for (int i = 1; i < len; i++) {
            char ch = str.charAt(i);
            if (i == 1) {
                dp1[i] = ch == '1' ? dp1[i - 1] : 1;
            } else {
                dp1[i] = ch == '1' ? dp1[i - 1] : dp1[i - 2] + 1;
            }
        }
        return Math.min(dp0[len - 1], dp1[len - 1]);
    }

    public int getSubarrayNum(ArrayList<Integer> a, int x) {
        // write code here
        int mod = (int) (1e9 + 7);
        int l = 0, r = -1;
        int ans = 0;
        int cnt5 = 0;
        int cnt2 = 0;
        int len = a.size();
        while (r < len) {
            ++r;
            if (r < len) {
                int num = a.get(r);
                cnt5 += getCnt5(num);
                cnt2 += getCnt2(num);
            }
            while (Math.min(cnt5, cnt2) >= x) {
                ans = (ans + len - r) % mod;
                cnt5 -= getCnt5(a.get(l));
                cnt2 -= getCnt2(a.get(l));
                ++l;
            }
        }
        return ans;
    }

    public int getCnt5(int num) {
        int cnt5 = 0;
        while (num % 5 == 0) {
            cnt5++;
            num /= 5;
        }
        return cnt5;
    }

    public int getCnt2(int num) {
        int cnt2 = 0;
        while (num % 2 == 0) {
            cnt2++;
            num /= 2;
        }
        return cnt2;
    }

    /**
     * 左右子树权值相等，那么即当每个节点值为1时，以当前树深度的完全二叉树的节点个数即为答案
     *
     * @param tree
     * @return
     */
    public int getTreeSum(TreeNode tree) {
        int mod = (int) (1e9 + 7);
        int height = getHeight(tree);
        int ans = 1;
        while (height > 0) {
            ans = (ans * 2) % mod;
            height--;
        }
        return ans;
    }

    public int getHeight(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        int leftHeight = getHeight(tree.left);
        int rightHeight = getHeight(tree.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }


    public long beautifulSubarrays(int[] nums) {
        // 2 ^ 17
        // 不会超过18位
        int[] bits = new int[25];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long ans = 0;
        for (int num : nums) {
            int count = 0;
            while (num > 0) {
                int bit = num % 2;
                bits[count] += bit;
                num /= 2;
                count++;
            }
            int sum = 0;
            for (int i = 0; i < bits.length; i++) {
                int bit = bits[i];
                if ((bit & 1) == 1) {
                    sum += Math.pow(2, i);
                }
            }
            ans += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    public static int findMinimumTime(int[][] tasks) {
        // 每个时间点可以进行的所有任务
        Point[] points = new Point[2001];
        //任务和其完成所需的时间
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 2000; i++) {
            points[i] = new Point(i);
        }
        for (int i = 0; i < tasks.length; i++) {
            int[] task = tasks[i];
            int start = task[0];
            int end = task[1];
            int duration = task[2];
            for (int j = start; j <= end; j++) {
                points[j].taskList.add(i);
            }
            map.put(i, duration);
        }
        PriorityQueue<Point> queue = new PriorityQueue<>(
                (o1, o2) -> o2.taskList.size() - o1.taskList.size()
        );
        // init
        for (int i = 1; i <= 2000; i++) {
            if (points[i].taskList.size() > 0) {
                queue.offer(points[i]);
            }
        }
        int ans = 0;
        while (!queue.isEmpty() && map.size() > 0) {
            Point point = queue.poll();
            for (int taskNum : point.taskList) {
                if (map.get(taskNum) != null) {
                    map.put(taskNum, map.get(taskNum) - 1);
                    if (map.get(taskNum) == 0) {
                        map.remove(taskNum);
                        ArrayList<Point> tem = new ArrayList<>();
                        while (!queue.isEmpty()) {
                            Point point1 = queue.poll();
                            if (point1.taskList.contains(taskNum)) {
                                point1.taskList.remove((Integer) taskNum);
                            }
                            tem.add(point1);
                        }
                        for (Point point1 : tem) {
                            queue.offer(point1);
                        }
                    }
                }
            }
            ans++;
        }
        return ans;
    }

    static class Point {
        int val;
        List<Integer> taskList;

        Point(int val) {
            this.val = val;
            taskList = new ArrayList<>();
        }
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] cnt = new int[26];
        int plen = p.length();
        int slen = s.length();
        for (int i = 0; i < plen; i++) {
            char ch = p.charAt(i);
            cnt[ch - 'a']++;
        }
        // init
        int size = 0;
        int l = 0;
        int r = -1;
        while (r < slen) {
            ++r;
            if (r < slen) {
                char rch = s.charAt(r);
                cnt[rch - 'a']--;
            }
            if (size < plen) {
                ++size;
            }
            if (size == plen) {
                if (check(cnt)) {
                    ans.add(l);
                }
                if (l < slen) {
                    char lch = s.charAt(l);
                    cnt[lch - 'a']++;
                    l++;
                }
            }
        }
        return ans;
    }

    public static boolean check(int[] cnt) {
        for (int val : cnt) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }

    public static int maxSumDivThree(int[] nums) {
        List<Integer> reminder1 = new ArrayList<>();
        List<Integer> reminder2 = new ArrayList<>();
        int sum = 0;
        for (int num : nums) {
            if (num % 3 == 0) {
                sum += num;
            } else if (num % 3 == 1) {
                reminder1.add(num);
            } else {
                reminder2.add(num);
            }
        }
        //每一个组成3个，护着reminder1 + reminder2
        reminder1.sort(Comparator.reverseOrder());
        reminder2.sort(Comparator.reverseOrder());
        int i = 0, j = 0;
        int len1 = reminder1.size(), len2 = reminder2.size();
        // 如果两个列表长度相同，则直接进行相加
        // 对于长的列表，如果二者相加，剩下的是(长列表长度-短列表长度)的元素
        // 如果是
        if (len1 == len2) {
            while (i < len1 && j < len2) {
                sum += reminder1.get(i) + reminder2.get(j);
                i++;
                j++;
            }
        } else {
            List<Integer> longList, shortList;
            if (len1 > len2) {
                longList = reminder1;
                shortList = reminder2;
            } else {
                longList = reminder2;
                shortList = reminder1;
            }
            int sum1 = 0, sum2 = 0;
            int longLen = longList.size(), shortLen = shortList.size();
            while (i < longLen && j < shortLen) {
                sum1 += longList.get(i) + shortList.get(j);
                i++;
                j++;
            }
            while (i + 2 < longLen) {
                sum1 += longList.get(i) + longList.get(i + 1) + longList.get(i + 2);
                i += 3;
            }
            i = 0;
            j = 0;
            while (i + 2 < longLen && i < longLen - shortLen) {
                sum2 += longList.get(i) + longList.get(i + 1) + longList.get(i + 2);
                i += 3;
            }
            while (i < longLen && j < shortLen) {
                sum2 += longList.get(i) + shortList.get(j);
                i++;
                j++;
            }
            sum += Math.max(sum1, sum2);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {2, 19, 6, 16, 5, 10, 7, 4, 11, 6};
        maxSumDivThree(nums);
    }
}



