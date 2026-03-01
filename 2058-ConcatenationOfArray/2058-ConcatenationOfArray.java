// Last updated: 01/03/2026, 21:10:32
class Solution {
    public int[] getConcatenation(int[] nums) {
    int n = nums.length;
    int[] result = new int[2 * n];
    for (int i = 0; i < n; i++) {
        result[i] = nums[i];
        result[i + n] = nums[i];
    }
    return result;
}
}