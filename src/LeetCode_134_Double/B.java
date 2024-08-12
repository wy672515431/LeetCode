package LeetCode_134_Double;

import java.util.Arrays;

public class B {
    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        long ans = 0;
        Arrays.sort(enemyEnergies);
        if (currentEnergy < enemyEnergies[0]) {
            return 0;
        }
        long gain = 0;
        for (int i = 1; i < enemyEnergies.length; i++) {
            gain += enemyEnergies[i];
        }
        ans += (currentEnergy + gain) / enemyEnergies[0];
        return ans;
    }
}
