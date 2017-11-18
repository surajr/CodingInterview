class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        
        for(int i=0; i<nums.length; i++)
        {
            int cur = nums[i];
            while(i < nums.length-1 && nums[i]+1 == nums[i+1])
                i++;
            if(cur != nums[i])
                result.add(cur + "->" + nums[i]);
            else
                result.add(cur+"");
        }
        
        return result;
    }
}
