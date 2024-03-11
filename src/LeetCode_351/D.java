package LeetCode_351;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class D {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Robot[] robots = new Robot[n];
        // 向左行驶为-1, 向右行驶为1
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(positions[i], healths[i], directions.charAt(i) == 'L' ? -1 : 1, i);
        }
        Arrays.sort(robots, Comparator.comparingInt(a -> a.pos));
        Stack<Robot> stack = new Stack<>();
        for (Robot robot : robots) {
            if (stack.isEmpty()) {
                stack.push(robot);
            } else {
                while (!stack.isEmpty() && stack.peek().direction == 1 && robot.direction == -1) {
                    if (robot.health < stack.peek().health) {
                        stack.peek().health -= 1;
                        robot.health = 0;
                        break;
                    } else if (robot.health == stack.peek().health) {
                        stack.pop();
                        robot.health = 0;
                        break;
                    } else {
                        stack.pop();
                        robot.health -= 1;
                    }
                }
                if (robot.health != 0) {
                    stack.push(robot);
                }
            }
        }
        List<Robot> list = new ArrayList<>(stack);
        list.sort(Comparator.comparingInt(o -> o.index));

        return list.stream().map(o -> o.health).collect(Collectors.toList());
    }

    public static int[] advantageCount(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] ans = new int[len];
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(nums2[i], i);
        }
        Arrays.fill(ans, -1);
        Arrays.sort(nums1);
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.val));
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < len && j < len) {
            int num1 = nums1[i];
            int num2 = nodes[j].val;
            int index = nodes[j].index;
            if (num1 > num2) {
                ans[index] = num1;
                i++;
                j++;
            } else {
                list.add(num1);
                i++;
            }
        }
        assert list.size() == Arrays.stream(ans).filter(o -> o == -1).count();
        i = 0;
        j = 0;
        while (i < list.size() && j < len) {
            if (ans[j] == -1) {
                ans[j] = list.get(i);
                i++;
                j++;
            } else {
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {15, 15, 4, 5, 0, 1, 7, 10, 3, 1, 10, 10, 8, 2, 3};
        int[] nums2 = {4, 13, 14, 0, 14, 14, 12, 3, 15, 12, 2, 0, 6, 9, 0};
        advantageCount(nums1, nums2);
    }
}

class Node {
    int val;
    int index;

    public Node(int val, int index) {
        this.val = val;
        this.index = index;
    }
}

class Robot {
    int pos;
    int health;
    int direction;
    int index;

    public Robot(int pos, int health, int direction, int index) {
        this.pos = pos;
        this.health = health;
        this.direction = direction;
        this.index = index;
    }
}
