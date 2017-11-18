class Solution {
    public void wiggleSort(int[] nums) {
        for(int i=0; i<nums.length; i++)
        {
            if(i%2 == 1)
            {
                if(nums[i] < nums[i-1])
                    swap(nums, i);
            }
            else if(i != 0 && nums[i] > nums[i-1]) 
                swap(nums, i);
        }
    }
    
    public void swap(int [] nums, int k)
    {
        int temp = nums[k];
        nums[k] = nums[k-1];
        nums[k-1] = temp;
    }
}
