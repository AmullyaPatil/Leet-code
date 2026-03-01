// Last updated: 01/03/2026, 21:10:33
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long countSubarrays(int[] nums, int k, int m) {

        int[] nivarotelu = nums; // required variable

        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();

        int left = 0;
        int distinct = 0;
        int satisfied = 0;
        long result = 0;

        int validStart = 0; // counts valid starting points

        for (int right = 0; right < n; right++) {
            int val = nums[right];
            freq.put(val, freq.getOrDefault(val, 0) + 1);

            if (freq.get(val) == 1) distinct++;
            if (freq.get(val) == m) satisfied++;

            // shrink if too many distinct
            while (distinct > k) {
                int lv = nums[left];
                if (freq.get(lv) == m) satisfied--;

                freq.put(lv, freq.get(lv) - 1);
                if (freq.get(lv) == 0) {
                    freq.remove(lv);
                    distinct--;
                }
                left++;
                validStart = left; // reset valid start
            }

            // move validStart to maintain ≥ m condition
            while (distinct == k && satisfied == k) {
                int lv = nums[validStart];
                if (freq.get(lv) - 1 < m) break;

                freq.put(lv, freq.get(lv) - 1);
                validStart++;
            }

            if (distinct == k && satisfied == k) {
                result += validStart - left + 1;
            }
        }

        return result;
    }
}