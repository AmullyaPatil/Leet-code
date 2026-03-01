// Last updated: 01/03/2026, 21:10:42
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int current = 0;
        int best = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                current++;
                if (current > best) {
                    best = current;
                }
            } else {
                current = 0;
            }
        }

        return best;
    }
}