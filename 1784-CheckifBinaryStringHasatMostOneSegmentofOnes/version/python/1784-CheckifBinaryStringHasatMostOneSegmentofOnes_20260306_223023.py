# Last updated: 06/03/2026, 22:30:23
1class Solution(object):
2    def checkOnesSegment(self, s):
3        """
4        :type s: str
5        :rtype: bool
6        """
7        seen_one = False
8        zero_after_one = False
9
10        for ch in s:
11            if ch == '1':
12                if zero_after_one:
13                    return False
14                seen_one = True
15            else:
16                if seen_one:
17                    zero_after_one = True
18
19        return True