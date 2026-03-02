// Last updated: 02/03/2026, 09:33:55
1class Solution {
2    public int minSwaps(int[][] grid) {
3        int n = grid.length;
4        int[] lastOne = new int[n];
5
6        // 1. compute last index of 1 in each row (-1 if all zeros)
7        for (int i = 0; i < n; i++) {
8            int idx = -1;
9            for (int j = n - 1; j >= 0; j--) {
10                if (grid[i][j] == 1) {
11                    idx = j;
12                    break;
13                }
14            }
15            lastOne[i] = idx;
16        }
17
18        int swaps = 0;
19
20        // 2. greedy from top to bottom
21        for (int i = 0; i < n; i++) {
22            int pos = -1;
23            for (int j = i; j < n; j++) {
24                if (lastOne[j] <= i) {
25                    pos = j;
26                    break;
27                }
28            }
29            if (pos == -1) {
30                return -1; // impossible
31            }
32
33            // 3. bring row pos up to i by swapping
34            while (pos > i) {
35                int tmp = lastOne[pos];
36                lastOne[pos] = lastOne[pos - 1];
37                lastOne[pos - 1] = tmp;
38                pos--;
39                swaps++;
40            }
41        }
42
43        return swaps;
44    }
45}