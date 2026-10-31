class Solution {

    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[i][j][0] = paths arriving from the left
        // dp[i][j][1] = paths arriving from above
        int[][][] dp = new int[m][n][2];

        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int right = dp[i][j][0];
                int down = dp[i][j][1];

                if (grid[i][j] == 0) {

                    // Move right
                    if (j + 1 < n) {
                        dp[i][j + 1][0] = (int) (
                            (dp[i][j + 1][0] + (long) right + down) % MOD
                        );
                    }

                    // Move down
                    if (i + 1 < m) {
                        dp[i + 1][j][1] = (int) (
                            (dp[i + 1][j][1] + (long) right + down) % MOD
                        );
                    }

                } else {

                    // Mirror redirects rightward movement downward
                    if (i + 1 < m) {
                        dp[i + 1][j][1] = (int) (
                            (dp[i + 1][j][1] + (long) right) % MOD
                        );
                    }

                    // Mirror redirects downward movement rightward
                    if (j + 1 < n) {
                        dp[i][j + 1][0] = (int) (
                            (dp[i][j + 1][0] + (long) down) % MOD
                        );
                    }
                }
            }
        }

        return (int) (
            ((long) dp[m - 1][n - 1][0]
            + dp[m - 1][n - 1][1]) % MOD
        );
    }
}