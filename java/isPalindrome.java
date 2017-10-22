class Solution {
    public boolean isPalindrome(String s) {
        if(s.length() == 0) return true;
        
        int head = 0, tail = s.length() - 1;
        char cur, prev;
        while(head <= tail)
        {
            prev = s.charAt(head);
            cur = s.charAt(tail);
            if(!Character.isLetterOrDigit(prev)) head++;
            else if(!Character.isLetterOrDigit(cur)) tail--;
            else
            {
                if(Character.toLowerCase(prev) != Character.toLowerCase(cur)) return false;
                head++;
                tail--;
            }
        }
        return true;
    }
}
