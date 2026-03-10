// Last updated: 10/03/2026, 23:24:35
1class Solution {
2    public int numberOfStableArrays(int zero, int one, int limit) {
3        int MOD = 1000000007;
4        long[][] dp0 = new long[zero + 1][one + 1];
5        long[][] dp1 = new long[zero + 1][one + 1];
6
7        for (int i = 1; i <= Math.min(zero, limit); i++) dp0[i][0] = 1;
8        for (int j = 1; j <= Math.min(one, limit); j++) dp1[0][j] = 1;
9
10        for (int i = 1; i <= zero; i++) {
11            for (int j = 1; j <= one; j++) {
12
13                dp0[i][j] = (dp0[i - 1][j] + dp1[i - 1][j]) % MOD;
14                if (i - limit - 1 >= 0) {
15                    dp0[i][j] = (dp0[i][j] - dp1[i - limit - 1][j] + MOD) % MOD;
16                }
17
18                dp1[i][j] = (dp0[i][j - 1] + dp1[i][j - 1]) % MOD;
19                if (j - limit - 1 >= 0) {
20                    dp1[i][j] = (dp1[i][j] - dp0[i][j - limit - 1] + MOD) % MOD;
21                }
22            }
23        }
24
25        return (int)((dp0[zero][one] + dp1[zero][one]) % MOD);
26    }
27}