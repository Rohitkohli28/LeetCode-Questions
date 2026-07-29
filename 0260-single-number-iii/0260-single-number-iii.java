class Solution {
    public int[] singleNumber(int[] nums) {
         int xor = 0;
        for(int num : nums){
            xor ^= num;
        }
        int diff = xor & (-xor);

        int num1 = 0, num2 = 0;
        for(int num : nums){
            if((num & diff) == 0){
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        int ans[] = new int[2];
        ans[0] = num1;
        ans[1] = num2;
        return ans;
    }
}