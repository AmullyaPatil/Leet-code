class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        if (zero == 0 && one == 0) return 1;
        int[][][][] dp = new int[zero + 1][one + 1][2][limit + 1];

        if (zero > 0) {
            dp[1][0][0][1] = 1; 
        }
        if (one > 0) {
            dp[0][1][1][1] = 1;
        }

        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                if (i == 0 && j == 0) continue;
                if (i > 0) {
                    for (int r = 1; r <= limit; r++) {
                        int ways = dp[i - 1][j][0][r];
                        if (ways != 0) {
                            if (r + 1 <= limit) {
                                dp[i][j][0][r + 1] = (dp[i][j][0][r + 1] + ways) % MOD;
                            }
                        }
                    }
                    for (int r = 1; r <= limit; r++) {
                        int ways = dp[i - 1][j][1][r];
                        if (ways != 0) {
                            dp[i][j][0][1] = (dp[i][j][0][1] + ways) % MOD;
                        }
                    }
                }
                if (j > 0) {
                    for (int r = 1; r <= limit; r++) {
                        int ways = dp[i][j - 1][1][r];
                        if (ways != 0) {
                            if (r + 1 <= limit) {
                                dp[i][j][1][r + 1] = (dp[i][j][1][r + 1] + ways) % MOD;
                            }
                        }
                    }
                    for (int r = 1; r <= limit; r++) {
                        int ways = dp[i][j - 1][0][r];
                        if (ways != 0) {
                            dp[i][j][1][1] = (dp[i][j][1][1] + ways) % MOD;
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int r = 1; r <= limit; r++) {
            ans = (ans + dp[zero][one][0][r]) % MOD;
            ans = (ans + dp[zero][one][1][r]) % MOD;
        }
        return (int) ans;
    }
}
