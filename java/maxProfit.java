class Solution {
    public int maxProfit(int[] prices) {
        
        if(prices.length == 0) return 0;
        
        int profit = 0, bought = prices[0];
        for(int i=1; i<prices.length; i++)
        {
            if(bought < prices[i])
                profit = Math.max(profit, prices[i]-bought);
            else
                bought = prices[i];
        }
        return profit;
    }
}
