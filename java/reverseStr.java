class Solution {
    public String reverseStr(String s, int k) {
        
        char [] words = s.toCharArray();
        for(int i=0; i<words.length; i+= 2*k)
        {
            reverse(words, i,  i+k);
        }
        return String.valueOf(words);        
    }
    
    public void reverse(char [] words, int start, int end)
    {
        end = Math.min(words.length, end) - 1;
        while(start < end)
        {
            char temp = words[start];
            words[start] = words[end];
            words[end] = temp;
            start++;
            end--;
        }
    }
}
