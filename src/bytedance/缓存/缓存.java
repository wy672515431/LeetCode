package bytedance.缓存;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class 缓存 {
    // 缓存一般有set、get、超时清除功能
    // 超时清除，有LRU和LFU算法
    // LRU算法(Least Recently Used)，选择最近且最久未使用的节点删除
    // LFU算法(Least Frequency Used)，选择使用次数最少的节点删除

    static class LRUCache {
        private final int capacity;
        private final Map<Integer, DLinkedNode> innerCache;
        // 维持一个双向链表, 伪节点
        private final DLinkedNode dummyNode;
        static class DLinkedNode {
            int key;
            int val;
            DLinkedNode prev, next;
            DLinkedNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.innerCache = new HashMap<>();
            this.dummyNode = new DLinkedNode(-1, -1);
            dummyNode.prev = dummyNode;
            dummyNode.next = dummyNode;
        }

        public int get(int key) {
            boolean exist = innerCache.containsKey(key);
            if (exist) {
                DLinkedNode curNode = innerCache.get(key);
                // 移动到链表末尾, 链表前面代表最久未使用
                removeAndMove(curNode);
                return curNode.val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            boolean exist = innerCache.containsKey(key);
            if (exist) {
                // 如果存在，获得其在map中对应的节点
                DLinkedNode curNode = innerCache.get(key);
                // 更新值
                curNode.val = value;
                // 此时，该节点是最新使用的，应该要把其移动到列表末尾
                removeAndMove(curNode);
            } else {
                // 如果不存在, 创建新节点，并且添加到cache
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 判断容量
                int size = innerCache.size();
                if (size < capacity) {
                    // 没有扩容，只用将节点加入到链表末尾
                    moveToTail(newNode);
                } else {
                    // 如果容量超过了,我们需要删除链表中的第一个节点
                    DLinkedNode firstNode = dummyNode.next;
                    removeNode(firstNode);
                    innerCache.remove(firstNode.key);
                    moveToTail(newNode);
                }
                innerCache.put(key, newNode);
            }
        }

        private void removeAndMove(DLinkedNode curNode) {
            removeNode(curNode);
            moveToTail(curNode);
        }

        private void moveToTail(DLinkedNode curNode) {
            // dummyNode.prev为最后一个节点，dummyNode.next为第一个节点
            curNode.next = dummyNode;
            curNode.prev = dummyNode.prev;
            dummyNode.prev.next = curNode;
            dummyNode.prev = curNode;
        }

        private void removeNode(DLinkedNode curNode) {
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
        }


    }

    static class LFUCache {

        // 我们需要额外来存储每个节点的使用频率
        static class DLinkedNode {
            int key;
            int val;
            // 储存频率
            int freq;
            DLinkedNode prev, next;
            DLinkedNode(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }
        }

        // 将所有频率相同的节点放到一个列表里面，每个列表里面遵循LRU算法，移除最久未使用的节点
        static class DLinkedList {
            int size;
            // 伪节点
            DLinkedNode dummyNode;
            DLinkedList() {
                size = 0;
                dummyNode = new DLinkedNode(-1, -1, -1);
                dummyNode.prev = dummyNode;
                dummyNode.next = dummyNode;
            }

            public void removeNode(DLinkedNode curNode) {
                curNode.prev.next = curNode.next;
                curNode.next.prev = curNode.prev;
                size--;
            }

            public void removeAndMove(DLinkedNode curNode) {
                removeNode(curNode);
                moveToTail(curNode);
            }

            public void moveToTail(DLinkedNode curNode) {
                // dummyNode.prev为最后一个节点，dummyNode.next为第一个节点
                curNode.next = dummyNode;
                curNode.prev = dummyNode.prev;
                dummyNode.prev.next = curNode;
                dummyNode.prev = curNode;
                size++;
            }
        }

        private int capacity;
        private int minFreq;
        private Map<Integer, DLinkedList> innerCache;
        private Map<Integer, DLinkedNode> keyMap;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
            innerCache = new HashMap<>();
            keyMap = new HashMap<>();
        }

        /**
         * 1. 如果key不存在，返回-1
         * 2. 如果存在
         * 3. 将该节点从原列表中去除，如果去除后列表为空，删除列表，同时如果minFreq == curNode.freq, minFreq++
         * 4. 将该节点加入到新列表中
         * 5. curNode.freq++
         * @param key
         * @return
         */
        public int get(int key) {
            boolean exist = keyMap.containsKey(key);
            if (exist) {
                DLinkedNode curNode = keyMap.get(key);
                DLinkedList originalList = innerCache.get(curNode.freq);
                originalList.removeNode(curNode);
                if (originalList.size == 0) {
                    innerCache.remove(curNode.freq);
                    // 同时，看一下是否频率等于最小频率，如果是最小频率，则最小频率加1
                    if (minFreq == curNode.freq) {
                        minFreq++;
                    }
                }
                // 节点频率加1
                curNode.freq++;
                // 添加到新列表中
                DLinkedList curList = innerCache.getOrDefault(curNode.freq, new DLinkedList());
                curList.moveToTail(curNode);
                innerCache.put(curNode.freq, curList);
                return curNode.val;
            } else {
                return -1;
            }
        }

        /**
         * 如果存在，则跟get一样，只是修改value值
         * 如果不存在，分两种情况
         * 1. 未达到容量，直接添加
         * 2. 达到容量，删除minFreq列表中的第一个节点，同时在keyMap中清除
         * 3. minFreq 需要在最后置为1
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            boolean exist = keyMap.containsKey(key);
            if (exist) {
                // 如果存在
                DLinkedNode curNode = keyMap.get(key);
                // 修改value值
                curNode.val = value;
                // 从原列表删除
                DLinkedList originalList = innerCache.get(curNode.freq);
                originalList.removeNode(curNode);
                // 看原列表是否为空，如果为空则要清空
                if (originalList.size == 0) {
                    innerCache.remove(curNode.freq);
                    // 同时，看一下是否频率等于最小频率，如果是最小频率，则最小频率加1
                    if (minFreq == curNode.freq) {
                        minFreq++;
                    }
                }
                // 节点频率加1
                curNode.freq++;
                // 添加到新列表中
                DLinkedList curList = innerCache.getOrDefault(curNode.freq, new DLinkedList());
                curList.moveToTail(curNode);
                innerCache.put(curNode.freq, curList);
            } else {
                // 如果不存在
                DLinkedNode curNode = new DLinkedNode(key, value, 1);
                int size = keyMap.size();
                if (size < capacity) {
                    // 未达到容量
                    DLinkedList curList = innerCache.getOrDefault(curNode.freq, new DLinkedList());
                    // 添加即可
                    curList.moveToTail(curNode);
                    innerCache.put(curNode.freq, curList);
                } else {
                    // 达到容量
                    DLinkedList minFreqList = innerCache.get(minFreq);
                    // 去除最久未使用的节点
                    DLinkedNode firstNode = minFreqList.dummyNode.next;
                    minFreqList.removeNode(firstNode);
                    // 同时在keyMap中清除
                    keyMap.remove(firstNode.key);
                    if (minFreqList.size == 0) {
                        innerCache.remove(firstNode.freq);
                        // 不需要增加minFreq,因为后面都会被置为1
                    }
                    DLinkedList curList = innerCache.getOrDefault(curNode.freq, new DLinkedList());
                    // 添加即可
                    curList.moveToTail(curNode);
                    innerCache.put(curNode.freq, curList);
                }
                // 最小频率为1
                minFreq = 1;
                keyMap.put(key, curNode);
            }
        }
    }
}
