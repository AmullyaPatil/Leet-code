// Last updated: 01/03/2026, 21:10:24
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;

class Node {
    int row, val;
    Node(int r, int v) { row = r; val = v; }
}

class Solution {
    public int minimumOR(int[][] grid) {
        int m = grid.length;
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        Set<Long> seen = new HashSet<>();

        for (int v : grid[0]) {
            pq.add(new Node(1, v));
            seen.add((1L << 32) | v);
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.row == m) return cur.val;

            int r = cur.row;
            for (int v : grid[r]) {
                int nv = cur.val | v;
                long key = (((long) (r+1)) << 32) | nv;
                if (!seen.contains(key)) {
                    pq.add(new Node(r+1, nv));
                    seen.add(key);
                }
            }
        }
        return 0;
    }
}