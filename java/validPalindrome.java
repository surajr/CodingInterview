/*
    Two pointers to check if s is a palindrome. When it's not a palindrome, skip one char either from left or right to see if it still forms a palindrome. Return true if it's a palindrome else false 

*/

class Solution {
    public boolean validPalindrome(String s) {        
        
        if(s.length()==0) return false;
        
        int left = 0, right = s.length()-1;        
        while(left <= right && s.charAt(left) == s.charAt(right))
        {
            left ++; right --;
        }
        
        if(left > right) return true;
        
        if(isPalindrome(s, left+1, right) || isPalindrome(s, left, right-1)) return true;
        
        return false;        
    }
    
    public boolean isPalindrome(String s, int l, int r)
    {
        while(l <= r && s.charAt(l) == s.charAt(r))
        {
            l++; r--;
        }        
        return l > r ? true : false;
            
    }
    
}
