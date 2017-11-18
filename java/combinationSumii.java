class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, result, new ArrayList<Integer>(), target, 0);
        return result;
    }
    
    public void backtrack(int [] candidates, List<List<Integer>> result, List<Integer> temp, int stepDiff, int start)
    {
        if(stepDiff < 0) return;
        else if(stepDiff == 0) result.add(new ArrayList<Integer>(temp));
        else
        {
            for(int i=start; i<candidates.length; i++)
            {
                if(i>start && candidates[i] == candidates[i-1]) continue;
                temp.add(candidates[i]);
                backtrack(candidates, result, temp, stepDiff-candidates[i], i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
}
