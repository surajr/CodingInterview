/*
For every number at i, Flip the number. If the number is repeated next time and if number is less than 0,
then it's a duplicate. Add this number to result.
T - O(n)
S - O(1)
*/

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            int index = Math.abs(nums[i])-1;
            if(nums[index] < 0)
                result.add(Math.abs(nums[i]));
            else
                nums[index] = -nums[index];
        }
        return result;
    }
}

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans=new ArrayList<>();
        int n=nums.length;
        int[] freq=new int[n];
        for(int i=0 ; i<n ; i++){
            freq[nums[i]-1]++;
        }
        for(int i=0 ; i<n ; i++){
            if(freq[i]==2){
                ans.add(i+1);
            }
        }
        return ans;
    }
}
