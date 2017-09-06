class Solution {
    public int findMaxLength(int[] nums) {
        
        if(nums.length == 0)
            return 0;
        
        HashMap<Integer, Integer> sumToMax = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++)
            if(nums[i]==0) nums[i] = -1;
        
        int max = 0, sum = 0;
        
        sumToMax.put(0, -1);
        
        for(int i=0; i<nums.length; i++)
        {
            sum += nums[i];
            if(sumToMax.containsKey(sum))
                max = Math.max(max, i - sumToMax.get(sum));
            else
                sumToMax.put(sum,i);
        }
        
        return max;
        
    }
}
