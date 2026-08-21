class Solution {
    public int subarraySum(int[] nums, int k) {

        // Brute Force 
        // int count = 0;

        // for(int i=0; i < nums.length; i++){
        //     int sum = 0;

        //     for(int j = i; j < nums.length; j++){
        //         sum += nums[j];

        //         if(sum == k){
        //             count++;
        //         }
        //     }
        // }
        // return count;

        // Optimse 
       HashMap<Integer, Integer> map = new HashMap<>();
       map.put(0, 1);

       int prefixSum = 0;
       int count = 0;

       for(int num : nums){
        prefixSum += num;

        int needed = prefixSum - k;

        count += map.getOrDefault(needed, 0);
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
       }
       return count;
    }
}