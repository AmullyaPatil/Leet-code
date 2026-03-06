class Solution {
    public boolean checkOnesSegment(String s) {
        boolean seenOne = false;
        boolean zeroAfterOne = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '1') {
                if (zeroAfterOne) {
                    return false;
                }
                seenOne = true;
            } else {
                if (seenOne) {
                    zeroAfterOne = true;
                }
            }
        }
        return true;
    }
}
