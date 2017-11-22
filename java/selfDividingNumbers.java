class Solution {
    
    public List<Integer> selfDividingNumbers(int left, int right) {        
        List<Integer> result = new ArrayList<>();
        
        for(int i=left; i<=right; i++)
            if(selfDivide(i))
                result.add(i);
                
        return result;
    }
    
    public boolean selfDivide(int num)
    {
        int original = num;
        while(num > 0)
        {
            if(num%10 == 0) return false ;
            if(original % (num%10) != 0) return false;
            num /= 10;
        }
        return true;       
    }
}
