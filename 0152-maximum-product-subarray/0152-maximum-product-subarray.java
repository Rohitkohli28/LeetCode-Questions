class Solution {
    public int maxProduct(int[] nums) {
        // int n = nums.length;
        // int maxSum = Integer.MIN_VALUE;

        // for (int start = 0; start < n; start++) {

        //     int product = 1;

        //     for (int end = start; end < n; end++) {

        //         product *= nums[end];

        //         maxSum = Math.max(maxSum, product);
        //     }
        // }

        // return maxSum;

        // Optimse
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int prevMax = maxProduct;
            int prevMin = minProduct;

            maxProduct = Math.max(
                nums[i],
                Math.max(prevMax * nums[i], prevMin * nums[i])
            );

            minProduct = Math.min(
                nums[i],
                Math.min(prevMax * nums[i], prevMin * nums[i])
            );

            answer = Math.max(answer, maxProduct);
        }

        return answer;
    }
}