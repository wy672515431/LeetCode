package bytedance.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 大礼包 {
    /**
     * 完全背包, dp会超时
     * @param price 单个价格
     * @param special 大礼包
     * @param needs 我的需求
     * @return
     */
//    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
//        int[][][][][][] dp = new int[11][11][11][11][11][11];
//        for (int i = 0; i < 11; i++) {
//            for (int j = 0; j < 11; j++) {
//                for (int k = 0; k < 11; k++) {
//                    for (int x = 0; x < 11; x++) {
//                        for (int y = 0; y < 11; y++) {
//                            Arrays.fill(dp[i][j][k][x][y], 0x3f3f3f3f);
//                        }
//                    }
//                }
//            }
//        }
//        dp[0][0][0][0][0][0] = 0;
//        // special 和 price都可以看作礼包，price知识礼包中的数量为1
//        for (int i = 0; i < special.size(); i++) {
//            int specialPrice = special.get(i).getLast();
//            // 一个整体
//            int[] nums = new int[6];
//            for (int j = 0; j < special.get(i).size() - 1; j++) {
//                nums[j] = special.get(i).get(j);
//            }
//            update(dp, nums, specialPrice);
//        }
//
//        for (int i = 0; i < price.size(); i++) {
//            int[] nums = new int[6];
//            nums[i] = 1;
//            update(dp, nums, price.get(i));
//        }
//
//        int[] nums = new int[6];
//        for (int i = 0; i < needs.size(); i++) {
//            nums[i] = needs.get(i);
//        }
//        return dp[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]][nums[5]];
//    }
//
//    private void update(int[][][][][][] dp, int[] upd, int price) {
//        for (int i = upd[0]; i < 11; i++) {
//            for (int j = upd[1]; j < 11; j++) {
//                for (int k = upd[2]; k < 11; k++) {
//                    for (int x = upd[3]; x < 11; x++) {
//                        for (int y = upd[4]; y < 11; y++) {
//                            for (int z = upd[5]; z < 11; z++) {
//                                dp[i][j][k][x][y][z] = Math.min(
//                                        dp[i][j][k][x][y][z],
//                                        dp[i - upd[0]][j - upd[1]][k - upd[2]][x - upd[3]][y - upd[4]][z - upd[5]] + price
//                                );
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
    // 记忆化搜索, 将状态建模为List, 用List<Integer>来表示一个状态
    Map<List<Integer>, Integer> memo = new HashMap<>();
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 首先过滤掉没用的礼包
        int n = price.size();
        List<List<Integer>> filterSpecial = new ArrayList<>();
        for (List<Integer> sp : special) {
            // 礼包价格必须小于等于单独购买的价格
            // 去掉礼包中总数为0
            int totalCount = 0, totalPrice = 0;
            for (int i = 0; i < sp.size() - 1; i++) {
                totalCount += sp.get(i);
                totalPrice += sp.get(i) * price.get(i);
            }
            if (totalCount > 0 && totalPrice > sp.getLast()) {
                filterSpecial.add(sp);
            }
        }
        return dfs(price, filterSpecial, needs, n);
    }

    public int dfs(List<Integer> price, List<List<Integer>> filterSpecial, List<Integer> needs, int n) {
        if (!memo.containsKey(needs)) {
            int minPrice = 0;
            // 单独购买进行初始化
            for (int i = 0; i < n; i++) {
                minPrice += needs.get(i) * price.get(i);
            }
            for (List<Integer> special: filterSpecial) {
                int specialPrice = special.getLast();
                List<Integer> nextNeeds = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    // 超出了需求
                    if (special.get(i) > needs.get(i)) {
                        break;
                    }
                    nextNeeds.add(needs.get(i) - special.get(i));
                }
                // 能到达下一状态
                if (nextNeeds.size() == n) {
                    int res = dfs(price, filterSpecial, nextNeeds, n);
                    minPrice = Math.min(minPrice, res + specialPrice);
                }
            }
            memo.put(needs, minPrice);
        }
        return memo.get(needs);
    }
}
