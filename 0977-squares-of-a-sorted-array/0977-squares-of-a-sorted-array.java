class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int low = 0;
        int high = n-1;
        int index = n-1;

        while(low <= high){
            if(Math.abs(nums[low]) > Math.abs(nums[high])){
                ans[index] = nums[low] * nums[low];
                low++;
            } else {
                ans[index] = nums[high] * nums[high];
                high--;
            }
            index--;
        }
        return ans;
    }
}