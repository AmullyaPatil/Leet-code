class Solution {
    public int minOperations(String s) {
        int diffTo010 = 0;
        int diffTo101 = 0; 
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char expected010 = (i % 2 == 0) ? '0' : '1';
            char expected101 = (i % 2 == 0) ? '1' : '0';

            if (c != expected010) diffTo010++;
            if (c != expected101) diffTo101++;
        }

        return Math.min(diffTo010, diffTo101);
    }
}
