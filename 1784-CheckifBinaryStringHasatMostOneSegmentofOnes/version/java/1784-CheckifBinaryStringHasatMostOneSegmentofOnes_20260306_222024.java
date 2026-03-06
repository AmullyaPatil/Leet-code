// Last updated: 06/03/2026, 22:20:24
1class Solution {
2    public boolean checkOnesSegment(String s) {
3        boolean seenOne = false;
4        boolean zeroAfterOne = false;
5
6        for (int i = 0; i < s.length(); i++) {
7            char ch = s.charAt(i);
8            if (ch == '1') {
9                if (zeroAfterOne) {
10                    return false;
11                }
12                seenOne = true;
13            } else {
14                if (seenOne) {
15                    zeroAfterOne = true;
16                }
17            }
18        }
19        return true;
20    }
21}