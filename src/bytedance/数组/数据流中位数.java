package bytedance.数组;

import java.util.PriorityQueue;

public class 数据流中位数 {

}

class MedianFinder {
    // 中位数：有序集合、要找到中间的数
    PriorityQueue<Integer> minQueue;
    PriorityQueue<Integer> maxQueue;
    int median;
    public MedianFinder() {
        minQueue = new PriorityQueue<>((a, b) -> b - a);
        maxQueue = new PriorityQueue<>();
    }


    public void addNum(int num) {
        if (minQueue.isEmpty() || num <= minQueue.peek()) {
            minQueue.offer(num);
            // size(minQueue) >= size(maxQueue) && size(minQueue) - size(maxQueue) <= 1
            if (maxQueue.size() + 1 < minQueue.size()) {
                maxQueue.offer(minQueue.poll());
            }
        } else {
            maxQueue.offer(num);
            if (maxQueue.size() > minQueue.size()) {
                minQueue.offer(maxQueue.poll());
            }
        }
    }

    /**
     * 判断minQueue和maxQueue的大小，如果minQueue的大小大于maxQueue的大小，那么中位数就是minQueue的堆顶元素
     * 否则，各自堆顶元素相加除以2
     * @return
     */
    public double findMedian() {
        if (minQueue.size() > maxQueue.size()) {
            return minQueue.peek();
        }
        return (minQueue.peek() + maxQueue.peek()) / 2.0;
    }
}
