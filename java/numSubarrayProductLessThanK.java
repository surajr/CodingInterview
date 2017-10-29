//Expand the window as large as possible; If the product exceeds k, then move the window from left. 

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0, left = 0, product = 1;
        
        if(k<=1) return 0;
        
        for(int right=0; right<nums.length; right++)
        {
            product *= nums[right];
            
            while(product >= k)
                product /= nums[left++];
            
            count += right - left + 1;
        }
        return count;
    }
}
