class Solution {
    public int majorityElement(int[] nums) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        
        for(int num:nums)
        {
            if(!map.containsKey(num))
                map.put(num, map.getOrDefault(num,0)+1);
            else
                map.put(num, map.get(num)+1);
            
            if(map.get(num) > nums.length / 2)
                result = num;
        }
        return result;
        
    }
}
