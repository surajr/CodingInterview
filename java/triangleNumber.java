public class Solution {
    public int triangleNumber(int[] nums) {
        if(nums.length == 0)
            return 0;
        
        Arrays.sort(nums);
        int count = 0;
        for(int i=2; i<nums.length; i++)
        {
            int j = 0, k = i-1;
            while(j < k)
            {
                if(nums[j]+nums[k] > nums[i])
                {
                    count += k-j;
                    k--;
                }
                else j++;
                    
            }
        }
        return count;
    }
}
