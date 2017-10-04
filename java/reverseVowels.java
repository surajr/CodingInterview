class Solution {
    public String reverseVowels(String s) {
        if(s == null) return null;
        String vowels = "aeiouAEIOU";
        char[] word = s.toCharArray();
        int start = 0, end = word.length - 1;
        
        while(start < end)
        {
            while(start < end && !vowels.contains(word[start]+""))
                start++;
            
            while(start<end && !vowels.contains(word[end]+""))
                end--;
            
            char temp = word[start];
            word[start] = word[end];
            word[end] = temp;
            
            start++;
            end--;
        }
        return new String(word);        
    }
}
