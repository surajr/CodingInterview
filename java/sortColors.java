class Solution {
    public void sortColors(int[] nums) {
        int blue = nums.length-1, red = 0;
        for(int i=0; i<=blue; i++)
        {
            while(nums[i]==2 && i<blue) swap(nums,i,blue--);
            while(nums[i]==0 && red<i) swap(nums,i,red++);
        }
    }
    
    public void swap(int [] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}
