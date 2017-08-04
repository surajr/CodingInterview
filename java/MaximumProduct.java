public class Solution {
    
    /*
    Trace the algorithm with this example: -8, -4, -2, 0, 1. 
    Identify 3 Maximum numbers: -2, 0, 1
    2 Smallest numbers: -8, -4
    
    max1 * max2 * max3 = 0
    min1 * min2 * max1 = -8 * -4 * 1 = 32 -> Return as answer
    */

// Time O(NLogN) Space O(N) - Because of searching
    public int maximumProduct(int[] nums) 
    {
        Arrays.sort(nums);
        int len = nums.length - 1;
        int end = nums[len-2] * nums[len-1] * nums[len];
        int start = nums[0] * nums[1] * nums[len];
        
        return Math.max(end, start);
    }
}

// Solution 2

//Time O(N) Space O(1)
public class Solution {
    
    public int maximumProduct(int[] nums) 
    {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = max1;
        int min1 = Integer.MAX_VALUE, min2 = min1;
        
        for(int n : nums)
        {
            if(n < min1)
            {
                min2 = min1;
                min1 = n;
            }
            
            else if ( n < min2)
                min2 = n;
            
            if(n > max1)
            {
                max3 = max2;
                max2 = max1;
                max1 = n;
            }
            else if(n > max2)
            {
                max3 = max2;
                max2 = n;
            }
            else if(n > max3)
            {
                max3 = n;
            }
        }
        
        return max1 * Math.max(min1 * min2, max2 * max3);       
        
    }
}
