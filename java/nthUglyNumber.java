/*

 The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
     because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:
     (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5. Then we use similar merge method as merge sort, to get every ugly number from the three subsequence. Every step we choose the smallest one, and move one step after.

*/

class Solution {
    public int nthUglyNumber(int n) {
        
        if(n == 1) return 1;
        
        int[] dp = new int[n+1];
        dp[1]=1;
        int p2 = 1, p3 = 1, p5 = 1;
        
        for(int i=2; i<=n; i++)
        {
            dp[i] = Math.min(p2*2, Math.min(p3*3, p5*5));
            
            if(dp[i] == 2*dp[p2]) p2++;
            if(dp[i] == 3*dp[p3]) p3++;
            if(dp[i] == 5*dp[p5]) p5++;
        }        
        return dp[n];
        
    }
}
