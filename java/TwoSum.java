/*
    Do you need explanation for this? 
    Put the array values into a Hashmap and search for target-array[i] everytime
    Space O(n) - Time O(n)
*/

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i< nums.length; i++)
        {
            if(map.containsKey(target-nums[i]))
            {
                return new int [] {map.get(target-nums[i]), i};
            }
            else
                map.put(nums[i], i);
        }
        
        return new int [] {0,0};
        
    }
}
