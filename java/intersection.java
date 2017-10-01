/*

Using HashSet
Time complexity O(n)
Space Complexity O(n)

*/

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        
        for(int num: nums1)
            set.add(num);
        
        for(int num: nums2)
            if(set.contains(num))
                result.add(num);
                
        int [] resultArray = new int[result.size()];
        int i = 0;
        
        for(int num: result)
            resultArray[i++] = num;
        
        return resultArray;
        
    }
}

/*

Using Sorting and Two pointers
Time complexity O(nlogn)
Space complexity O(1)

*/



