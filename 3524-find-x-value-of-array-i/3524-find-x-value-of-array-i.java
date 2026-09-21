class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];

            int numMod = num % k;

            // Start a new subarray with the current number
            newDp[numMod] = 1;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                int newRemainder = (int) ((1L * r * numMod) % k);

                newDp[newRemainder] += dp[r];
            }

            // Add current subarray counts to the answer
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}