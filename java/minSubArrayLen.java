class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, min = Integer.MAX_VALUE, j =0, sum = 0;
        
        while(j<nums.length)
        {
            sum += nums[j++];
            while(sum >= s)
            {
                min = Math.min(min, j-left);
                sum -= nums[left++];
            }
        }
        
        return min == Integer.MAX_VALUE? 0: min;
    }
}
