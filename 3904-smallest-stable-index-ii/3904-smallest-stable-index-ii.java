class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] input = nums;

        
        int[] prefMax = new int[n];
        prefMax[0] = input[0];
        for (int i = 1; i < n; i++) {
            prefMax[i] = Math.max(prefMax[i - 1], input[i]);
        }

        int[] sufMin = new int[n];
        sufMin[n - 1] = input[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sufMin[i] = Math.min(sufMin[i + 1], input[i]);
        }

        for (int i = 0; i < n; i++) {
            if (prefMax[i] - sufMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}