class Solution {
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) return "";
        
        String [] numStr = new String[nums.length];
        
        for(int i=0; i<nums.length; i++)
            numStr[i] = nums[i]+"";
        
        Arrays.sort(numStr, new Comparator<String>() 
                    {
                        @Override
                        public int compare(String i, String j)
                        {
                            String s1 = i+j;
                            String s2 = j+i;
                            return s1.compareTo(s2);
                        }
                    }                   
                   );
        
        if(numStr[nums.length-1].charAt(0) == '0') return "0";
        
        String result = new String();
        for(int i=0; i<numStr.length; i++)
            result = numStr[i]+result;
        
        return result;
    }
}
