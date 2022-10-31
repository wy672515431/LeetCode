package LeetCode.Tree;

import LeetCode.TreeNode;

import java.util.Arrays;
import java.util.stream.Collectors;

public class 最大二叉树 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};
        constructMaximumBinaryTree(nums);
    }
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    public static TreeNode construct(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int maxPosition = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] > nums[maxPosition]) {
                maxPosition = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxPosition]);
        TreeNode left = construct(nums, start, maxPosition - 1);
        TreeNode right = construct(nums, maxPosition + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }
}
