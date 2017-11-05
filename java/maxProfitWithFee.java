class Solution {
    public int maxProfit(int[] prices, int fee) {
        int buy = Integer.MIN_VALUE, sale = 0;
        
        for(int price:prices)
        {
            buy = Math.max(buy, sale-price);
            sale = Math.max(sale, buy+price-fee);
        }
        return sale;
    }
}
