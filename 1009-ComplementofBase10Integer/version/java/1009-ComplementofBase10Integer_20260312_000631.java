// Last updated: 12/03/2026, 00:06:31
1class Solution {
2    public int bitwiseComplement(int n) {
3        if (n == 0) return 1; // edge case: 0 -> "0", complement "1" -> 1
4
5        // Create a mask with all bits up to the highest set bit as 1s.
6        int mask = 0;
7        int temp = n;
8        while (temp > 0) {
9            mask = (mask << 1) | 1;
10            temp >>= 1;
11        }
12        return n ^ mask; // flip bits inside the relevant width
13    }
14}