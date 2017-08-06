public class Solution 
{
    // This problem is exactly similar to longest palindromic substring. Just need to add count++.
    // Generate all the substrings of a string and expand around the centre to see if it formas a palindrome
    private int count = 0;
    
    public int countSubstrings(String s) 
    {
        for(int i=0; i<s.length(); i++)
        {
            isPalindrome(i,i,s); //Even length
            isPalindrome(i,i+1,s); //Odd length
        }
        return count;
    }
    
    public void isPalindrome(int left, int right, String s)
    {
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right))
        {
            count++; left--; right++;
        }         
    }
}
