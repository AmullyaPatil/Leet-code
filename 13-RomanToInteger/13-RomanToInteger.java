// Last updated: 01/03/2026, 21:10:43
class Solution {
    public int romanToInt(String s) {
        int n = s.length();
        int total = 0;
        int prev = 0;

        for (int i = n - 1; i >= 0; i--) {
            int cur;
            switch (s.charAt(i)) {
                case 'I': cur = 1; break;
                case 'V': cur = 5; break;
                case 'X': cur = 10; break;
                case 'L': cur = 50; break;
                case 'C': cur = 100; break;
                case 'D': cur = 500; break;
                case 'M': cur = 1000; break;
                default: throw new IllegalArgumentException("Invalid Roman numeral: " + s.charAt(i));
            }
            if (cur < prev) total -= cur;
            else {
                total += cur;
                prev = cur;
            }
        }
        return total;
    }
}