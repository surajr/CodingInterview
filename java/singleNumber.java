class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for(int num:nums)
            diff ^= num;
        
        int [] res = {0,0};
        diff &= -diff;
        
        for(int num: nums)
        {
            if((num & diff)==0)
                res[0] ^= num;
            else
                res[1] ^= num;
        }
        return res;
    }
}
