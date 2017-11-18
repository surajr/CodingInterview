class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }
    
    public void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int start)
    {
        result.add(new ArrayList<Integer>(temp));
        for(int i=start; i<nums.length; i++)
        {
            if(i>start && nums[i] == nums[i-1]) continue;
            temp.add(nums[i]);
            backtrack(result, temp, nums, i+1);
            temp.remove(temp.size()-1);
        }        
    }
}
