class Solution {
    public int longestOnes(int[] nums, int k) {
        // Brute force Approach

        // int n = nums.length;
        // int maxLength = 0;

        // for(int i = 0; i < n; i++){
        //     int zeroCount = 0;
        //     for(int j = i; j < n; j++){
        //         if(nums[j] == 0){
        //         zeroCount++;
        //         }
        //         if(zeroCount > k){
        //             break;
        //         }
        //         maxLength = Math.max(maxLength, j - i + 1);
        //     }
        // }
        // return maxLength;


        // Sliding window
        int left = 0;
        int zeros = 0; 
        int max = 0;


        for(int right = 0; right < nums.length; right++){
            if(nums[right] == 0){
                zeros++;
            }

            while(zeros > k){
                if(nums[left] == 0){
                    zeros--;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}