// Last updated: 08/03/2026, 19:26:29
1class Solution {
2    public String findDifferentBinaryString(String[] nums) {
3        int n = nums.length;
4        char[] ans = new char[n];
5        for (int i = 0; i < n; i++) {
6            ans[i] = nums[i].charAt(i) == '0' ? '1' : '0';
7        }
8        return new String(ans);
9    }
10}