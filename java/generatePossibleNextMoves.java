public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();
        
        for(int i=1; i<s.length(); i++)
        {
            if(s.charAt(i-1) == '+' && s.charAt(i)=='+')
                result.add(s.substring(0,i-1) + "--" + s.substring(i+1,s.length()));
        }
        
        return result;
    }
}
