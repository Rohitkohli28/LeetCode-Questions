class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        // Special case : only 1 house 
        if(n == 1){
            return nums[0];
        }

        int[] dp = new int[n];

        // Base case
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // Fill the DP Array
        for(int i = 2; i<n; i++){
            int pick = nums[i] + dp[i - 2]; 
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n - 1];
    }
}