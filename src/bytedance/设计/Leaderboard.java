package bytedance.设计;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Leaderboard {
    // k - pid v - score
    Map<Integer, Integer> map;
    // k - score v - num of players with this score
    TreeMap<Integer, Integer> scores;
    public Leaderboard() {
        map = new HashMap<>();
        scores = new TreeMap<>(Collections.reverseOrder());
    }

    public void addScore(int playerId, int score) {
        if (!map.containsKey(playerId)) {
            map.put(playerId, score);
            scores.put(score, scores.getOrDefault(score, 0) + 1);
        } else {
            int oldScore = map.get(playerId);
            int newScore = oldScore + score;
            map.put(playerId, newScore);
            int oldCount = scores.get(oldScore);
            if (oldCount == 1) {
                scores.remove(oldScore);
            } else {
                scores.put(oldScore, oldCount - 1);
            }
            scores.put(newScore, scores.getOrDefault(newScore, 0) + 1);
        }
    }

    public int top(int K) {
        int count = K;
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
            int score = entry.getKey();
            int times = entry.getValue();
            if (count > times) {
                sum += score * times;
                count -= times;
            } else {
                sum += score * count;
                break;
            }
        }
        return sum;
    }

    public void reset(int playerId) {
        int score = map.get(playerId);
        map.remove(playerId);
        int count = scores.get(score);
        if (count == 1) {
            scores.remove(score);
        } else {
            scores.put(score, count - 1);
        }
    }
}

