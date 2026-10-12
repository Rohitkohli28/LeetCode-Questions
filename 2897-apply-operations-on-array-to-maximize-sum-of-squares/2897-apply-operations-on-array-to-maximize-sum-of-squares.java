class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        int[] count = new int[31];

        // Step 1: Count set bits at each position
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    count[bit]++;
                }
            }
        }

        long ans = 0;

        // Step 2: Construct k maximum numbers greedily
        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    x |= (1L << bit);
                    count[bit]--;
                }
            }

            // Step 3: Add square modulo MOD
            ans = (ans + x * x) % MOD;
        }

        return (int) ans;
    }
}