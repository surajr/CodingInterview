public class Solution {
    public int uniquePaths(int m, int n) {
        
        int [][] dp = new int [m][n];
        
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
                dp[i][j] = i ==0 || j == 0 ? 1 : dp[i-1][j] + dp[i][j-1];
        
        return dp[m-1][n-1];
    }
}
