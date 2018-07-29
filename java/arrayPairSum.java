/*
We need to form the pairings of the array's elements such that the overall sum of the minimum out of such pairings is maximum. Thus, we can look at the operation of choosing the minimum out of the pairing, say (a, b)(a,b) as incurring a loss of a - ba−b(if a> ba>b), in the maximum sum possible.

The total sum will now be maximum if the overall loss incurred from such pairings is minimized. This minimization of loss in every pairing is possible only if the numbers chosen for the pairings lie closer to each other than to the other elements of the array.

Taking this into consideration, we can sort the elements of the given array and form the pairings of the elements directly in the sorted order. This will lead to the pairings of elements with minimum difference between them leading to the maximization of the required sum.

O(nlon n)
O(1)
*/


public class Solution {
    public int arrayPairSum(int[] nums) {
        if(nums.length == 0) return 0;
        Arrays.sort(nums);
        int sum = 0;
        for(int i=0; i<nums.length; i++)
        {
            sum += nums[i];
            i++;
        }
        return sum;
    }
}
