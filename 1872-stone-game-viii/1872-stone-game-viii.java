class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        int sum = 0;
        for (int x : stones) {
            sum += x;
        }

        int dp = sum;

        for (int i = n - 1; i >= 2; i--) {
            sum -= stones[i];

            dp = Math.max(dp, sum - dp);
        }

        return dp;
    }
}