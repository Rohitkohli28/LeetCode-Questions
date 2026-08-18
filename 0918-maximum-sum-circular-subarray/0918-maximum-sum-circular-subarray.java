class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        // int n = nums.length;
        // int maxSum = Integer.MIN_VALUE;

        // for(int start = 0; start < n; start++){
        //     int sum = 0;

        //     for(int count = 0; count < n; count++){
        //         int index = (start + count) % n;
        //         sum += nums[index];
        //         maxSum = Math.max(maxSum, sum);
        //     }
        // }
        // return maxSum;

        // Optimse
        int totalSum = 0;

        int currentMax = nums[0];
        int maxSum = nums[0];

        int currentMin = nums[0];
        int minSum = nums[0];

        for (int i = 0; i < nums.length; i++) {

            totalSum += nums[i];

            if (i > 0) {

                currentMax = Math.max(
                        nums[i],
                        currentMax + nums[i]);

                maxSum = Math.max(maxSum, currentMax);

                currentMin = Math.min(
                        nums[i],
                        currentMin + nums[i]);

                minSum = Math.min(minSum, currentMin);
            }
        }

        // Important special case
        if (maxSum < 0) {
            return maxSum;
        }

        return Math.max(maxSum, totalSum - minSum);
    }
}