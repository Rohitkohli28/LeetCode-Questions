class Solution {
    public int maxSubArray(int[] nums) {
        // Brute force
        // int n = nums.length;
        // int maxSum = Integer.MIN_VALUE;

        // for (int start = 0; start < n; start++) {

        //     int sum = 0;

        //     for (int end = start; end < n; end++) {

        //         sum += nums[end];

        //         maxSum = Math.max(maxSum, sum);
        //     }
        // }

        // return maxSum;

        // Optimise 
        int currentSum = nums[0];
        int maxSum = nums[0];

        for(int i = 1; i < nums.length; i++){
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}