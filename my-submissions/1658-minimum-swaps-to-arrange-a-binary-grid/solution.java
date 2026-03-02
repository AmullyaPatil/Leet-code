class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] lastOne = new int[n];

        // 1. compute last index of 1 in each row (-1 if all zeros)
        for (int i = 0; i < n; i++) {
            int idx = -1;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    idx = j;
                    break;
                }
            }
            lastOne[i] = idx;
        }

        int swaps = 0;

        // 2. greedy from top to bottom
        for (int i = 0; i < n; i++) {
            int pos = -1;
            for (int j = i; j < n; j++) {
                if (lastOne[j] <= i) {
                    pos = j;
                    break;
                }
            }
            if (pos == -1) {
                return -1; // impossible
            }

            // 3. bring row pos up to i by swapping
            while (pos > i) {
                int tmp = lastOne[pos];
                lastOne[pos] = lastOne[pos - 1];
                lastOne[pos - 1] = tmp;
                pos--;
                swaps++;
            }
        }

        return swaps;
    }
}
