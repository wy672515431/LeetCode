package LeetCode_138_Double;

import java.util.PriorityQueue;

public class D {
    public long minDamage(int power, int[] damage, int[] health) {
        int n = damage.length;
        Node[] enemies = new Node[n];
        // 每个敌人消灭所耗时间为 (health[i] - 1 ) / power + 1
        // 消灭敌人之前我受到的伤害是 damage[i] * ((health[i] - 1) / power + 1)
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum += damage[i];
        }
        // k1 * (b1 - x)
        // k2 * (b2 - x)
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.weight, a.weight));
        for (int i = 0; i < n; i++) {
            enemies[i] = new Node(damage[i], health[i],
                    (1.0 * damage[i]) / ((health[i] - 1) / power + 1));
            pq.offer(enemies[i]);
        }
        long ans = 0L;
        // 每次删除一个后，权重就会发生变化
        // 如何处理权重的变化

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            ans += (sum - cur.damage) * ((cur.health - 1) / power + 1);
            ans += (long) (cur.damage) * ((cur.health - 1) / power + 1);
            sum -= cur.damage;
        }
        return ans;
    }

    static class Node {
        int damage;
        int health;
        double weight;

        Node(int damage, int health, double weight) {
            this.damage = damage;
            this.health = health;
            this.weight = weight;
        }
    }
}
