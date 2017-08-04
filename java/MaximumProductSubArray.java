public class Solution {
    public int maxProduct(int[] nums) 
    {
        int currMax = nums[0], maxi = nums[0], mini = nums[0];
        
        for(int i=1; i < nums.length; i++)
        {
            if(nums[i] < 0)
            {
                // Swap max and Min because multiplying -ve number with +ve yields in -ve. So reverse it
                int temp = maxi;
                maxi = mini;
                mini = temp;
            }    
            
            // Current max is either the current element i.e nums[i] or max * num[i] 
            // Current min is either the current element i.e nums[i] or min * num[i]
            maxi = Math.max(nums[i], maxi * nums[i]);
            mini = Math.min(nums[i], mini * nums[i]);
            
            currMax = Math.max(currMax, maxi);            
        }
        
        return currMax;        
    }    
}
