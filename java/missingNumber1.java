class Solution {
    public int missingNumber(int[] nums) {
            
        int var = nums.length;
        
        for(int i=0; i<nums.length; i++)
            var ^= i ^ nums[i];
        
        return var;
        
    }
}


class Solution {
    public int missingNumber(int[] nums) {
            
        Set<Integer> hashset = new HashSet<>();
        
        for(int num : nums)
            hashset.add(num);
        
        for(int i=0; i<nums.length+1; i++)
            if(!hashset.contains(i))
                return i;
        
        return -1;
        
    }
}


