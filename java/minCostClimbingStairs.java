class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int a = cost[0];
        int b = cost[1];
        int result = Math.min(a,b);
        
        for(int i=2; i<=cost.length; i++){
            int step = (i == cost.length? 0:cost[i]);
            result = Math.min(step+a, step+b);
            a = b;
            b = result;
        }
        return result;        
    }
}
