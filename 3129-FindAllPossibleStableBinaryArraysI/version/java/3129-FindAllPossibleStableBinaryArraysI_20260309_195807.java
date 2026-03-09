// Last updated: 09/03/2026, 19:58:07
1class Solution {
2    static final int MOD = 1_000_000_007;
3
4    public int numberOfStableArrays(int zero, int one, int limit) {
5        if (zero == 0 && one == 0) return 1;
6        int[][][][] dp = new int[zero + 1][one + 1][2][limit + 1];
7
8        if (zero > 0) {
9            dp[1][0][0][1] = 1; 
10        }
11        if (one > 0) {
12            dp[0][1][1][1] = 1;
13        }
14
15        for (int i = 0; i <= zero; i++) {
16            for (int j = 0; j <= one; j++) {
17                if (i == 0 && j == 0) continue;
18                if (i > 0) {
19                    for (int r = 1; r <= limit; r++) {
20                        int ways = dp[i - 1][j][0][r];
21                        if (ways != 0) {
22                            if (r + 1 <= limit) {
23                                dp[i][j][0][r + 1] = (dp[i][j][0][r + 1] + ways) % MOD;
24                            }
25                        }
26                    }
27                    for (int r = 1; r <= limit; r++) {
28                        int ways = dp[i - 1][j][1][r];
29                        if (ways != 0) {
30                            dp[i][j][0][1] = (dp[i][j][0][1] + ways) % MOD;
31                        }
32                    }
33                }
34                if (j > 0) {
35                    for (int r = 1; r <= limit; r++) {
36                        int ways = dp[i][j - 1][1][r];
37                        if (ways != 0) {
38                            if (r + 1 <= limit) {
39                                dp[i][j][1][r + 1] = (dp[i][j][1][r + 1] + ways) % MOD;
40                            }
41                        }
42                    }
43                    for (int r = 1; r <= limit; r++) {
44                        int ways = dp[i][j - 1][0][r];
45                        if (ways != 0) {
46                            dp[i][j][1][1] = (dp[i][j][1][1] + ways) % MOD;
47                        }
48                    }
49                }
50            }
51        }
52        long ans = 0;
53        for (int r = 1; r <= limit; r++) {
54            ans = (ans + dp[zero][one][0][r]) % MOD;
55            ans = (ans + dp[zero][one][1][r]) % MOD;
56        }
57        return (int) ans;
58    }
59}