// Last updated: 01/03/2026, 21:10:39
class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[2*n];
        for(int i =0; i <n;i++){
            result[2 * i ] = nums[i];
            result[2*i+1] = nums[i +n];
        }
        return result;
    }
}