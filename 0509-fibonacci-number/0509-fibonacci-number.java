class Solution {
    public int fib(int n) {

        // Approch 1 --> Recusrion
        // if(n == 0){
        //     return 0;
        // }
        // if(n == 1){
        //     return 1;
        // }
        // return fib(n-1) + fib(n-2);


        // Apprach 2 --> Memoization
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        return solve(n , dp);
    }

    private int solve(int n, int[] dp){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }

        // check already calculated or not
        if(dp[n] != -1){
            return dp[n];
        }

        // calculate and store
        dp[n] = solve(n-1, dp) + solve(n - 2, dp);
        return dp[n];
    }
}