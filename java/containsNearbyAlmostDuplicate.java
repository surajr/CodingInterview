class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || t < 0 || k < 0) return false;
        
        TreeSet<Integer> set = new TreeSet<>();
        
        for(int i=0; i<nums.length; i++)
        {
            if(i-k-1 >= 0)
                set.remove(nums[i-k-1]);
            int n = nums[i];
            
            if(set.floor(n) != null && n <= t+set.floor(n) || set.ceiling(n) != null && n + t >= set.ceiling(n))
                return true;
            
            set.add(n);
        }
        return false;
    }
}
