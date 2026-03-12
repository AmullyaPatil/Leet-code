// Last updated: 12/03/2026, 23:33:12
1import java.util.*;
2
3public class Solution {
4    // DSU
5    static class DSU {
6        int[] p, r;
7        int comp;
8        DSU(int n) {
9            p = new int[n];
10            r = new int[n];
11            for (int i = 0; i < n; i++) p[i] = i;
12            comp = n;
13        }
14        int find(int x) {
15            if (p[x] != x) p[x] = find(p[x]);
16            return p[x];
17        }
18        boolean union(int a, int b) {
19            int ra = find(a), rb = find(b);
20            if (ra == rb) return false;
21            if (r[ra] < r[rb]) {
22                p[ra] = rb;
23            } else if (r[ra] > r[rb]) {
24                p[rb] = ra;
25            } else {
26                p[rb] = ra;
27                r[ra]++;
28            }
29            comp--;
30            return true;
31        }
32        boolean connected() {
33            return comp == 1;
34        }
35    }
36
37    public int maxStability(int n, int[][] edges, int k) {
38        int m = edges.length;
39        int maxSi = 0;
40        for (int[] e : edges) maxSi = Math.max(maxSi, e[2]);
41        int lo = 0, hi = maxSi * 2;
42        int best = -1;
43
44        // Pre-store edges by type
45        // We'll re-check per S; but for speed we keep arrays
46        while (lo <= hi) {
47            int mid = lo + (hi - lo) / 2;
48            if (feasible(n, edges, k, mid)) {
49                best = mid;
50                lo = mid + 1;
51            } else {
52                hi = mid - 1;
53            }
54        }
55
56        // Ensure there exists a spanning tree at all (best may be -1 if impossible)
57        return best;
58    }
59
60    private boolean feasible(int n, int[][] edges, int k, int S) {
61        DSU dsu = new DSU(n);
62
63        // First pass: musti=1 edges
64        for (int[] e : edges) {
65            int u = e[0], v = e[1], s = e[2], must = e[3];
66            if (must == 1) {
67                if (s < S) {
68                    // cannot upgrade
69                    return false;
70                }
71                dsu.union(u, v);
72            }
73        }
74
75        // If already cycle detected, DSU union would just ignore; we need to detect cycle among forced edges.
76        // To detect cycle among forced edges, redo with a fresh DSU to track cycles specifically.
77        // Simpler: run a separate pass to detect cycle among must==1 edges.
78
79        // Detect cycle among forced edges properly
80        DSU forced = new DSU(n);
81        for (int[] e : edges) {
82            int u = e[0], v = e[1], s = e[2], must = e[3];
83            if (must == 1) {
84                if (s < S) return false;
85                if (!forced.union(u, v)) {
86                    return false; // cycle among forced edges
87                }
88            }
89        }
90
91        // Start from forced connectivity
92        // We'll use a fresh DSU combining forced unions
93        DSU dsu2 = new DSU(n);
94        // apply forced unions from 'forced'
95        for (int i = 0; i < n; i++) {
96            dsu2.p[i] = forced.p[i];
97            dsu2.r[i] = forced.r[i];
98        }
99        dsu2.comp = forced.comp;
100
101        // Collect edges usable without upgrade first
102        List<int[]> upgradeEdges = new ArrayList<>(); // edges that require upgrade (si*2 >= S and si < S)
103        List<int[]> freeEdges = new ArrayList<>(); // si >= S and must be 0
104        for (int[] e : edges) {
105            int u = e[0], v = e[1], s = e[2], must = e[3];
106            if (must == 0) {
107                if (s >= S) {
108                    freeEdges.add(new int[]{u, v});
109                } else if (s * 2 >= S) {
110                    upgradeEdges.add(new int[]{u, v});
111                } // else unusable
112            }
113        }
114
115        // First, add all free edges (si>=S) to connect as much as possible
116        for (int[] ed : freeEdges) {
117            dsu2.union(ed[0], ed[1]);
118        }
119
120        // Now, try to add upgrade edges, counting upgrades used
121        int upgradesUsed = 0;
122        for (int[] ed : upgradeEdges) {
123            if (dsu2.union(ed[0], ed[1])) {
124                upgradesUsed++;
125                if (upgradesUsed > k) return false;
126            }
127        }
128
129        // Check if connected
130        return dsu2.connected();
131    }
132
133    // For testing
134    public static void main(String[] args) {
135        Solution sol = new Solution();
136        int n = 3;
137        int[][] edges = { {0,1,2,1}, {1,2,3,0} };
138        int k = 1;
139        System.out.println(sol.maxStability(n, edges, k)); // expect 2
140
141        int[][] e2 = { {0,1,4,0}, {1,2,3,0}, {0,2,1,0} };
142        System.out.println(sol.maxStability(3, e2, 2)); // expect 6
143
144        int[][] e3 = { {0,1,1,1}, {1,2,1,1}, {2,0,1,1} };
145        System.out.println(sol.maxStability(3, e3, 0)); // expect -1
146    }
147}