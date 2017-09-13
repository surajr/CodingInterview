/*
    The least significant bit which las 1 corresponds to 0 bit in n - 1.
    
    AND N and N-1 and keep doing till you hit 0. Count the number of steps and store it in array
    
    Time: O(nk) k - number of bits 
    Space O(1)

*/

class Solution {
    public int[] countBits(int num) {   
          
        int [] result = new int[num+1];
        
        for(int i=0; i<=num; i++)
            result[i]=hammingBits(i);
        return result;
        
    }
    
    public int hammingBits(int n)
    {
        int sum = 0;
        while(n !=0)
        {
            sum ++;
            n = n&n-1;
        }        
        return sum;
    }
}
