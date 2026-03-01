class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;
        for (int i = 0; i < n.length(); i++) {
            int d = n.charAt(i) - '0';
            if (d > maxDigit) maxDigit = d;
        }
        return maxDigit;
    }
}
