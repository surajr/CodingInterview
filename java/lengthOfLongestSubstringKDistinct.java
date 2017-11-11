class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0, len = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c,0)+1);
            
            while(map.size() > k)
            {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar)-1);
                if(map.get(leftChar)==0)
                    map.remove(leftChar);
                left++;
            }
            
            len = Math.max(len, i-left+1);
        }
        
        return len;
    }
}
