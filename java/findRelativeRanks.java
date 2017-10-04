class Solution {
    public String[] findRelativeRanks(int[] nums) {
        String [] result = new String[nums.length];
        
        if(nums.length == 0) return result;
        
        int [] temp = new int[nums.length];
        HashMap<Integer, String> map = new HashMap<>();
        
        System.arraycopy(nums, 0, temp, 0, nums.length);        
        Arrays.sort(temp);
        
        for(int i=0; i<temp.length; i++)
        {
            if(i < temp.length-3)
                map.put(temp[i], String.valueOf(temp.length - i));
            else if(i == temp.length -1)
                map.put(temp[i], "Gold Medal");
            else if(i == temp.length-2)
                map.put(temp[i], "Silver Medal");
            else if(i == temp.length-3)
                map.put(temp[i], "Bronze Medal");                
        }
        
        for(int i=0; i<nums.length; i++)
            result[i] = map.get(nums[i]);
        
        return result;
    }
}
