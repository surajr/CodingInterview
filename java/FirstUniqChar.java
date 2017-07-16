/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
*/


/* Get the frequency of each character.
*  Get the first character that has a frequency of one. (21 ms)
*/

public class Solution {
    public int firstUniqChar(String s) 
    {
        
        if(s.length() == 0)
            return -1;
        
        int [] arr = new int[26];        
        char [] c = s.toCharArray();
        
        for(int i=0; i < c.length; i++)
            arr[c[i] - 'a']++;
        
        for(int i=0; i < c.length; i++)
        {
            if(arr[c[i]-'a'] == 1)
                return i;
        }
        
        return -1;     
        
    }
}

/* The idea is to use a slow pointer to point to the current unique character and a fast pointer to scan the string. The fast pointer not only just add the 
*  count of the character. Meanwhile, when fast pointer finds the identical character of the character at the current slow pointer, we move the slow 
*  pointer to the next unique character or not visited character. (33 ms)
*/

public class Solution {
    public int firstUniqChar(String s) 
    {
        
        if(s.length() == 0)
            return -1;
        
        int [] counter = new int[256];
        char [] c = s.toCharArray();
        
        int slow = 0, fast = 0;
        
        while(fast < s.length())
        {
            counter[c[fast]]++;
            while(slow < s.length() && counter[c[slow]] > 1) slow++;
            
            if(slow == s.length()) return -1;
            fast++;
        }
        
        return slow;        
    }
}

/* With builtin String functions (40 ms) */

public class Solution {
    public int firstUniqChar(String s) 
    {
        
        if(s.length() == 0)
            return -1;
        
        for(int i=0; i < s.length(); i++)
        {
            if(s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))
                return i;
        }
        return -1;
        
    }
}
