/*

Start with Naive approach of copying non zero elements to different array and append zeros at the end till length of the list.
It's not best solution we are creating the copy.
So lets optimize this, shift non zero values as far as possible and fill the remaining elements with 0.
Here we did this in one pass, O(1) space and preserved the ordering of array elements.
Space: O(n)
Time: O(1)
*/

class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) return;
        Integer idx = 0;
        for(Integer n:nums){
            if( n!=0 ) nums[idx++] = n;
        }
        for(; idx<nums.length; idx++)
            nums[idx] = 0;
    }
}
