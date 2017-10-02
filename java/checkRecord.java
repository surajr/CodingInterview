class Solution {
    public boolean checkRecord(String s) {
        
        int count = 0;
        
        for(int i=0; i<s.length() && count < 2; i++)
        {
            if(s.charAt(i)=='A')
                count++;
        }
        
        return count < 2 && s.indexOf("LLL") < 0;
        
    }
}
