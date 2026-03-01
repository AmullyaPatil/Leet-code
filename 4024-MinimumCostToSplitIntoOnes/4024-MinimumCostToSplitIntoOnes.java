// Last updated: 01/03/2026, 21:10:26
import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();

    public int minCost(int n) {
        if (n <= 1) return 0;
        if (memo.containsKey(n)) return memo.get(n);

        int best = Integer.MAX_VALUE;
        for (int a = 1; a <= n / 2; a++) {
            int b = n - a;
            int cost = a * b + minCost(a) + minCost(b);
            if (cost < best) best = cost;
        }

        memo.put(n, best);
        return best;
    }
}