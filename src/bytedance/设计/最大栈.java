package bytedance.设计;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

class MaxStack {
    private Stack<int[]> stack;
    private PriorityQueue<int[]> priorityQueue;
    private Set<Integer> set;
    private int cnt;
    public MaxStack() {
        stack = new Stack<>();
        priorityQueue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return b[0] - a[0];
            }
        });
        set = new TreeSet<>();
        cnt = 0;
    }

    public void push(int x) {
        stack.push(new int[]{x, cnt});
        priorityQueue.offer(new int[]{x, cnt});
        cnt++;
    }

    public int pop() {
        while (set.contains(stack.peek()[1])) {
            stack.pop();
        }
        int[] val = stack.pop();
        set.add(val[1]);
        return val[0];
    }

    public int top() {
        while (set.contains(stack.peek()[1])) {
            stack.pop();
        }
        return stack.peek()[0];
    }

    public int peekMax() {
        while (set.contains(priorityQueue.peek()[1])) {
            priorityQueue.poll();
        }
        return priorityQueue.peek()[0];
    }

    public int popMax() {
        while (set.contains(priorityQueue.peek()[1])) {
            priorityQueue.poll();
        }
        int[] val = priorityQueue.poll();
        set.add(val[1]);
        return val[0];
    }
}
