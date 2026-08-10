class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {

        // Normal Scan
        // int count = 0;
        // int maxCount = 0;

        // for(int i=0; i<nums.length; i++){
        //     if(nums[i] == 1){
        //         count++;
        //     } else {
        //         maxCount = Math.max(maxCount, count);
        //         count = 0;
        //     }
        // }
        // return Math.max(maxCount, count);

        // SLiding window
        int left = 0;
        int max = 0;

        for(int right = 0; right < nums.length; right++){
            if(nums[right] == 0){
                left = right + 1;
            }

            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}