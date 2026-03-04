// Last updated: 04/03/2026, 20:46:52
1class Solution {
2    public int numSpecial(int[][] mat) {
3        int m = mat.length;
4        int n = mat[0].length;
5        int[] rowOnes = new int[m];
6        int[] colOnes = new int[n];
7        for (int i = 0; i < m; i++) {
8            for (int j = 0; j < n; j++) {
9                if (mat[i][j] == 1) {
10                    rowOnes[i]++;
11                    colOnes[j]++;
12                }
13            }
14        }
15        int count = 0;
16        for (int i = 0; i < m; i++) {
17            if (rowOnes[i] == 1) {
18                for (int j = 0; j < n; j++) {
19                    if (mat[i][j] == 1 && colOnes[j] == 1) {
20                        count++;
21                    }
22                }
23            }
24        }
25
26        return count;
27    }
28}