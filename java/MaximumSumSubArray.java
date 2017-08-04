public class Solution 
{
    public int maxSubArray(int[] nums) 
    {
        int max = nums[0], maxSoFar = nums[0];
        for(int i=1; i < nums.length; i++)
        {
            maxSoFar = maxSoFar > 0 ? maxSoFar + nums[i] : nums[i];
            max = Math.max(maxSoFar, max);            
        }
        return max;
    }
}
