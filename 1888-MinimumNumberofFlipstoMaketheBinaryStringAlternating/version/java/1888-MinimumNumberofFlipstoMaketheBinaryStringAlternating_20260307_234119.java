// Last updated: 07/03/2026, 23:41:19
1class Solution {
2    public int minFlips(String s) {
3        int n = s.length();
4        if (n <= 1) return 0;
5
6        String t = s + s;
7        int m = 2 * n;
8
9        int[] A = new int[m];
10        int[] B = new int[m];
11        for (int i = 0; i < m; i++) {
12            int bit = (t.charAt(i) == '1') ? 1 : 0;
13            A[i] = (bit != (i % 2)) ? 1 : 0;
14            B[i] = (bit != (1 - (i % 2))) ? 1 : 0;
15        }
16
17        // initial window [0, n-1]
18        int sumA = 0, sumB = 0;
19        for (int i = 0; i < n; i++) {
20            sumA += A[i];
21            sumB += B[i];
22        }
23        int best = Math.min(sumA, sumB);
24
25        for (int start = 1; start < n; start++) {
26            sumA += A[start + n - 1] - A[start - 1];
27            sumB += B[start + n - 1] - B[start - 1];
28            best = Math.min(best, Math.min(sumA, sumB));
29        }
30
31        return best;
32    }
33}