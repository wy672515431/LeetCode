package LeetCode;

import java.util.Deque;

class MyCircularQueue {
    int maxSize;
    int[] deque;
    int front;
    int rear;
    public MyCircularQueue(int k) {
        //队列为空时(front == rear)
        //队列为满时(front == (rear + 1) % maxSize)
        this.maxSize = k + 1;
        deque = new int[maxSize];
        front = 0;
        rear = 0;
    }
    
    public boolean enQueue(int value) {
        if (front == (rear + 1) % maxSize) {
            return false;
        }
        deque[rear] = value;
        rear = (rear + 1) % maxSize;
        return true;
    }
    
    public boolean deQueue() {
        if (front == rear) {
            return false;
        }
        front = (front + 1) % maxSize;
        return true;
    }
    
    public int Front() {
        if (front == rear) {
            return -1;
        }
        return deque[front];
    }
    
    public int Rear() {
        if (front == rear) {
            return -1;
        }
        return deque[(rear - 1 + maxSize) % maxSize];
    }
    
    public boolean isEmpty() {
        return front == rear;
    }
    
    public boolean isFull() {
        return front == (rear + 1) % maxSize;
    }
}