// Last updated: 15/03/2026, 19:16:01
1import java.util.ArrayList;
2import java.util.List;
3
4public class Fancy {
5    private static final long MOD = 1_000_000_007L;
6    private final List<Long> base;
7    private long mulAll;
8    private long addAll;
9
10    public Fancy() {
11        base = new ArrayList<>();
12        mulAll = 1L;
13        addAll = 0L;
14    }
15
16    public void append(int val) {
17        long invMul = modInverse(mulAll, MOD);
18        long baseVal = (val - addAll) % MOD;
19        if (baseVal < 0) baseVal += MOD;
20        baseVal = (baseVal * invMul) % MOD;
21        base.add(baseVal);
22    }
23
24    public void addAll(int inc) {
25        addAll = (addAll + inc) % MOD;
26    }
27
28    public void multAll(int m) {
29        mulAll = (mulAll * m) % MOD;
30        addAll = (addAll * m) % MOD;
31    }
32
33    public int getIndex(int idx) {
34        if (idx < 0 || idx >= base.size()) {
35            return -1;
36        }
37        long b = base.get(idx);
38        long val = (b * mulAll) % MOD;
39        val = (val + addAll) % MOD;
40        return (int) val;
41    }
42
43    private static long modPow(long a, long e, long mod) {
44        long res = 1;
45        a %= mod;
46        while (e > 0) {
47            if ((e & 1) == 1) res = (res * a) % mod;
48            a = (a * a) % mod;
49            e >>= 1;
50        }
51        return res;
52    }
53
54    private static long modInverse(long a, long mod) {
55        return modPow(a, mod - 2, mod);
56    }
57}