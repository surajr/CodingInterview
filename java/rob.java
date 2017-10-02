class Solution {
    public int rob(int[] nums) {
        
        int prevMax = 0, curMax = 0;
        
        for(int num : nums)
        {
            int temp = curMax;
            curMax = Math.max(prevMax + num, curMax);
            prevMax = temp;
        }
        return curMax;
    }
}
