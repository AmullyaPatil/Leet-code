// Last updated: 13/03/2026, 20:31:58
1class Solution {
2    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
3        int n = workerTimes.length;
4        long height = mountainHeight;
5
6        java.util.function.LongPredicate feasible = (long T) -> {
7            long total = 0;
8            for (int i = 0; i < n; i++) {
9                long w = workerTimes[i];
10                long limit = (2L * T) / w;
11                long x = (long) ((Math.sqrt(1.0 + 4.0 * limit) - 1.0) / 2.0);
12                while (x * (x + 1) > limit) x--;
13                while ((x + 1) * (x + 2) <= limit) x++;
14                total += x;
15                if (total >= height) return true;
16            }
17            return total >= height;
18        };
19
20        long lo = 0, hi = 1;
21        while (!feasible.test(hi)) {
22            hi <<= 1;
23        }
24        while (lo < hi) {
25            long mid = lo + (hi - lo) / 2;
26            if (feasible.test(mid)) {
27                hi = mid;
28            } else {
29                lo = mid + 1;
30            }
31        }
32        return lo;
33    }
34}