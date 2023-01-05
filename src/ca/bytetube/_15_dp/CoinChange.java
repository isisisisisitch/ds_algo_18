package ca.bytetube._15_dp;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println((Integer.MIN_VALUE));
    }

    /**
     * dp的核心思路：
     * 从找1分钱开始到找amount分钱所对应的硬币数依次统计出来
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];//0
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin || dp[i - coin] == -1) continue;
                min = Math.min(dp[i - coin], min);//dp[1] min
            }
            if (min == Integer.MAX_VALUE) dp[i] = -1;
            else  dp[i] = min + 1;
        }
        return dp[amount];
    }

    /**
     * dp的核心思路：
     * 从找1分钱开始到找amount分钱所对应的硬币数依次统计出来
     *
     * @param amount
     * @return
     */
    public static int coinChange(int amount) {
        int[] dp = new int[amount + 1];//0
        for (int i = 1; i <= amount; i++) {
            //dp(n) = min { dp(n – 25), dp(n – 20), dp(n – 5), dp(n – 1) } + 1
            int min = Integer.MAX_VALUE;
            if (i >= 1) min = Math.min(dp[i - 1], min);//4
            if (i >= 5) min = Math.min(dp[i - 5], min);//0
            if (i >= 20) min = Math.min(dp[i - 20], min);
            if (i >= 25) min = Math.min(dp[i - 25], min);
            dp[i] = min + 1;
        }

        return dp[amount];

    }


    public static int coinChange2(int amount) {

        int[] dp = new int[amount + 1];
        int[] coins = {1, 5, 20, 25};
        for (int coin : coins) {
            if (amount < coin) continue;
            dp[coin] = 1;
        }
        return coinChange2(amount, dp);
    }

    private static int coinChange2(int amount, int[] dp) {
        if (amount < 1) return Integer.MAX_VALUE;
        if (dp[amount] == 0) {
            int min1 = Math.min(coinChange2(amount - 25, dp), coinChange2(amount - 20, dp));
            int min2 = Math.min(coinChange2(amount - 5, dp), coinChange2(amount - 1, dp));
            dp[amount] = Math.min(min1, min2) + 1;
        }

        return dp[amount];
    }

    public static int coinChange1(int n) {//dp(n) n = 6
        if (n < 1) return Integer.MAX_VALUE;
        if (n == 25 || n == 20 || n == 5 || n == 1) return 1;
        int min1 = Math.min(coinChange1(n - 25), coinChange1(n - 20));
        int min2 = Math.min(coinChange1(n - 5), coinChange1(n - 1));
        return Math.min(min1, min2) + 1;

    }
}
