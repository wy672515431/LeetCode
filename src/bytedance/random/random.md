## 随机数问题
### Fisher Yates 洗牌算法
```
     public static void shuffle(int[] nums) {
        Random random = new Random();
        // 第二个数没有被交换的概率 n - 1 / n * (1 / n - 1)  = 1 / n
        // 依次类推
        for (int i = 0; i < nums.length; i++) {
            // 从[i, n)中随机选取一个数
            int j = i + random.nextInt(nums.length - i);
            // 将nums[i]与nums[j]交换
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
```

### 水塘抽样算法
```
    // 每个元素都有 k / n 的概率被选中
    // 1. 前k个元素直接放入sample中
    // 2. 对于第i个元素，以 k / i 的概率选择sample中的一个数，进行替换
    public static int[] sample(int[] nums, int k) {
        int[] sample = new int[k];
        Random random = new Random();
        // 前k个元素直接放入sample中
        for (int i = 0; i < k; i++) {
            sample[i] = nums[i];
        }
        // 前面k个元素被抽到的概率是
        // 1 * (k / i + 1) * (i + 1 / i + 2) * (n - 1 / n) = k / n
        for (int i = k; i < nums.length; i++) {
            int j = random.nextInt(i + 1);
            if (j < k) {
                sample[j] = nums[i];
            }
        }
        return sample;
    }
```

### 随机数生成
已知一随机发生器，产生0的概率是p，产生1的概率是1-p，构造一个发生器，使得它构造1、2、3的概率均为1/3；…。更一般地，构造一个发生器，使得它构造1、2、3、…n的概率均为1/n。

```
    显然，我们可以生成这样的序列，它们中1和0的数字个数相同， 0011 、 1100、1001，从中选取3个映射到1、2、3即可。
```


给出从n个数中随机选择1个的方法。注意，n非常大，并且一开始不知道其具体值。数字是一个一个给你的，当给完之后，你必须立刻给出随机的结果。

```
    水塘抽样算法
```