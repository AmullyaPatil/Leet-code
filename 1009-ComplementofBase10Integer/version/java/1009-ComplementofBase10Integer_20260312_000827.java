// Last updated: 12/03/2026, 00:08:27
1class Solution {
2    public int bitwiseComplement(int n) {
3        if (n == 0) return 1; 
4        int mask = 0;
5        int temp = n;
6        while (temp > 0) {
7            mask = (mask << 1) | 1;
8            temp >>= 1;
9        }
10        return n ^ mask;
11    }
12}