class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, s, new ArrayList<String>(), 0);
        return result;
    }
    
    public void backtrack(List<List<String>> result, String s, List<String> temp, int start)
    {
        if(start == s.length())
            result.add(new ArrayList<String>(temp));
        else
        {
            for(int i=start; i<s.length(); i++)
            {
                if(isPalindrome(s, start, i))
                {
                    temp.add(s.substring(start, i+1));
                    backtrack(result, s, temp, i+1);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
    
    public boolean isPalindrome(String s, int left, int right)
    {
        while(left < right)
        {
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
