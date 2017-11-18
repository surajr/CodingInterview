class Solution {
    public int findUnsortedSubarray(int[] nums) {
        
        int [] numsCopy = nums.clone();
        Arrays.sort(numsCopy);
        
        int start = 0, end = nums.length - 1;
        
        while(start < nums.length && nums[start] == numsCopy[start]) start++;
        
        while(end > start && nums[end] == numsCopy[end]) end--;
        
        return end-start+1;        
    }
}
