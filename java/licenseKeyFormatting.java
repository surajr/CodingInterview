class Solution {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        for(int i = S.length()-1; i>=0; i--)
        {            
            if(S.charAt(i) == '-') continue;            
            if(count % K == 0 && count > 0) sb.insert(0,"-"); 
            sb.insert(0, Character.toUpperCase(S.charAt(i)));
            count++;           
        }
        return sb.toString();
    }
}
