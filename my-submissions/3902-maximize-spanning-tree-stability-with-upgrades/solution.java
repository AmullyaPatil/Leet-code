import java.util.*;

public class Solution {
    // DSU
    static class DSU {
        int[] p, r;
        int comp;
        DSU(int n) {
            p = new int[n];
            r = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
            comp = n;
        }
        int find(int x) {
            if (p[x] != x) p[x] = find(p[x]);
            return p[x];
        }
        boolean union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return false;
            if (r[ra] < r[rb]) {
                p[ra] = rb;
            } else if (r[ra] > r[rb]) {
                p[rb] = ra;
            } else {
                p[rb] = ra;
                r[ra]++;
            }
            comp--;
            return true;
        }
        boolean connected() {
            return comp == 1;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int m = edges.length;
        int maxSi = 0;
        for (int[] e : edges) maxSi = Math.max(maxSi, e[2]);
        int lo = 0, hi = maxSi * 2;
        int best = -1;

        // Pre-store edges by type
        // We'll re-check per S; but for speed we keep arrays
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (feasible(n, edges, k, mid)) {
                best = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        // Ensure there exists a spanning tree at all (best may be -1 if impossible)
        return best;
    }

    private boolean feasible(int n, int[][] edges, int k, int S) {
        DSU dsu = new DSU(n);

        // First pass: musti=1 edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (s < S) {
                    // cannot upgrade
                    return false;
                }
                dsu.union(u, v);
            }
        }

        // If already cycle detected, DSU union would just ignore; we need to detect cycle among forced edges.
        // To detect cycle among forced edges, redo with a fresh DSU to track cycles specifically.
        // Simpler: run a separate pass to detect cycle among must==1 edges.

        // Detect cycle among forced edges properly
        DSU forced = new DSU(n);
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (s < S) return false;
                if (!forced.union(u, v)) {
                    return false; // cycle among forced edges
                }
            }
        }

        // Start from forced connectivity
        // We'll use a fresh DSU combining forced unions
        DSU dsu2 = new DSU(n);
        // apply forced unions from 'forced'
        for (int i = 0; i < n; i++) {
            dsu2.p[i] = forced.p[i];
            dsu2.r[i] = forced.r[i];
        }
        dsu2.comp = forced.comp;

        // Collect edges usable without upgrade first
        List<int[]> upgradeEdges = new ArrayList<>(); // edges that require upgrade (si*2 >= S and si < S)
        List<int[]> freeEdges = new ArrayList<>(); // si >= S and must be 0
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0) {
                if (s >= S) {
                    freeEdges.add(new int[]{u, v});
                } else if (s * 2 >= S) {
                    upgradeEdges.add(new int[]{u, v});
                } // else unusable
            }
        }

        // First, add all free edges (si>=S) to connect as much as possible
        for (int[] ed : freeEdges) {
            dsu2.union(ed[0], ed[1]);
        }

        // Now, try to add upgrade edges, counting upgrades used
        int upgradesUsed = 0;
        for (int[] ed : upgradeEdges) {
            if (dsu2.union(ed[0], ed[1])) {
                upgradesUsed++;
                if (upgradesUsed > k) return false;
            }
        }

        // Check if connected
        return dsu2.connected();
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] edges = { {0,1,2,1}, {1,2,3,0} };
        int k = 1;
        System.out.println(sol.maxStability(n, edges, k)); // expect 2

        int[][] e2 = { {0,1,4,0}, {1,2,3,0}, {0,2,1,0} };
        System.out.println(sol.maxStability(3, e2, 2)); // expect 6

        int[][] e3 = { {0,1,1,1}, {1,2,1,1}, {2,0,1,1} };
        System.out.println(sol.maxStability(3, e3, 0)); // expect -1
    }
}
