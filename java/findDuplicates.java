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


class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            int cur = Math.abs(nums[i]);
            if(nums[cur-1]<0){
                ans.add(cur);
            }else{
                nums[cur-1] = -1*nums[cur-1];
            }
        }
        return ans;
    }
}
