class Solution {
    public int maxAbsoluteSum(int[] nums) {
        // int ans = 0;

        // for (int i = 0; i < nums.length; i++) {
        //     for (int j = i; j < nums.length; j++) {
        //         int sum = 0;

        //         for (int k = i; k < j; k++) {
        //             sum += nums[k];
        //         }
        //         ans = Math.max(ans, Math.abs(sum));
        //     }
        // }
        // return ans;

        // Kadane
        int currMax = 0;
        int currMin = 0;
        int ans = 0;

        for(int x : nums){
            currMax = Math.max(x, currMax + x);
            currMin = Math.min(x, currMin + x);

            ans = Math.max(ans, Math.max(currMax, Math.abs(currMin)));
        }
        return ans;
    }
}