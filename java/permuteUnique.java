class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return result;
    }
    
    public void backtrack(List<List<Integer>> result, List<Integer> temp, int [] nums, boolean [] used)
    {
        if(temp.size() == nums.length)
            result.add(new ArrayList<>(temp));
        else
        {
            for(int i=0; i<nums.length; i++)
            {
                if(used[i] || i>0 && nums[i] == nums[i-1] && !used[i-1]) continue;
                used[i] = true;
                temp.add(nums[i]);
                backtrack(result, temp, nums, used);
                used[i] = false;
                temp.remove(temp.size()-1);
            }
        }
    }
}
