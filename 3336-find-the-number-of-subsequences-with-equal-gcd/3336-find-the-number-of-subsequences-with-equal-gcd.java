class Solution {
    static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int max = 200;

        long[][] dp = new long[max + 1][max + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            long[][] next = new long[max + 1][max + 1];

            for (int g1 = 0; g1 <= max; g1++) {
                for (int g2 = 0; g2 <= max; g2++) {

                    if (dp[g1][g2] == 0) {
                        continue;
                    }

                    long ways = dp[g1][g2];

                    // Choice 1: Don't take x
                    next[g1][g2] =
                        (next[g1][g2] + ways) % MOD;

                    // Choice 2: Put x in subsequence 1
                    int newG1 = gcd(g1, x);

                    next[newG1][g2] =
                        (next[newG1][g2] + ways) % MOD;

                    // Choice 3: Put x in subsequence 2
                    int newG2 = gcd(g2, x);

                    next[g1][newG2] =
                        (next[g1][newG2] + ways) % MOD;
                }
            }

            dp = next;
        }

        long ans = 0;

        // Both subsequences must be non-empty,
        // so GCD cannot be 0.
        for (int g = 1; g <= max; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return (int) ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}