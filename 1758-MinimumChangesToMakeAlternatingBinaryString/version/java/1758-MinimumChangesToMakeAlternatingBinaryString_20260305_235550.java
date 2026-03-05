// Last updated: 05/03/2026, 23:55:50
1class Solution {
2    public int minOperations(String s) {
3        int diffTo010 = 0;
4        int diffTo101 = 0; 
5        for (int i = 0; i < s.length(); i++) {
6            char c = s.charAt(i);
7            char expected010 = (i % 2 == 0) ? '0' : '1';
8            char expected101 = (i % 2 == 0) ? '1' : '0';
9
10            if (c != expected010) diffTo010++;
11            if (c != expected101) diffTo101++;
12        }
13
14        return Math.min(diffTo010, diffTo101);
15    }
16}