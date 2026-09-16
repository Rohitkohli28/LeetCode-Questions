class Solution {
    public int numberOfSets(int n, int k) {
        long[][] dp = new long[k + 1][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int segments = 1; segments <= k; segments++) {
            long sum = 0;

            for (int i = 1; i < n; i++) {
                sum = (sum + dp[segments - 1][i - 1]) % 1000000007;
                dp[segments][i] = (dp[segments][i - 1] + sum) % 1000000007;
            }
        }

        return (int) dp[k][n - 1];
    }
}