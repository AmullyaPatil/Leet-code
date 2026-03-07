class Solution {
    public int minFlips(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        String t = s + s;
        int m = 2 * n;

        int[] A = new int[m];
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            int bit = (t.charAt(i) == '1') ? 1 : 0;
            A[i] = (bit != (i % 2)) ? 1 : 0;
            B[i] = (bit != (1 - (i % 2))) ? 1 : 0;
        }

        // initial window [0, n-1]
        int sumA = 0, sumB = 0;
        for (int i = 0; i < n; i++) {
            sumA += A[i];
            sumB += B[i];
        }
        int best = Math.min(sumA, sumB);

        for (int start = 1; start < n; start++) {
            sumA += A[start + n - 1] - A[start - 1];
            sumB += B[start + n - 1] - B[start - 1];
            best = Math.min(best, Math.min(sumA, sumB));
        }

        return best;
    }
}
