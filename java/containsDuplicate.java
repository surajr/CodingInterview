/*
4 ways to solve this problem.

1. Searching: Iterate through the array. For every element A[i] in A, search for its occurence in A[0:i-1]. If found return true else containue O(n^2)
2. HashMap: Add every element to hashmap with a counter. If counter is > 1, then return true t: O(n) s: O(n)
3. Sorting: go through each element and compare previous and next element O(nlon n) O(1)
4. Set: If set contains element A[i] return true else keep contiunue O(n) O(n)

*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for(int num: nums)
        {
            if(set.contains(num))
                return true;
            else
                set.add(num);
        }

        return false;
    }
}



class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=1; i<nums.length; i++)
        {
            if(nums[i] == nums[i-1])
                return true;
        }
        return false;
    }
}
