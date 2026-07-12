class Solution {
    public int uniquePaths(int m, int n) 
    {
     int dp[][]=new int[m][n];
     for(int i=0;i<m;i++)
     {
        for(int j=0;j<n;j++)
        {
            dp[i][j]=-1;
        }
     }   
     for(int i=0;i<m;i++)
     {
        int left=0,up=0;
        for(int j=0;j<n;j++)
        {
            if(i==0 && j==0 )
            {
                dp[i][j]=1;
            }
            else
            {
                if(j>0)
                {
                   left=dp[i][j-1];
                }
                if(i>0)
                {
                     up=dp[i-1][j];
                }
                dp[i][j]=left+up;
            }
        }
     }
     return dp[m-1][n-1];
    }
}