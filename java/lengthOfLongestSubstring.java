class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int i=0, j=0, max=0;
        while(j<s.length()){
            if(set.contains(s.charAt(j)))
                set.remove(s.charAt(i++));
            else{
                set.add(s.charAt(j++));
                max = Math.max(max, set.size());
            }
        }
        return max;
    }
}


public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Queue<Character> queue = new LinkedList<>();
        int maxLen = 0;
        
        for(char c : s.toCharArray())
        {
            if(queue.contains(c))
                while(queue.remove() != c);
            
            queue.add(c);
            
            if(queue.size() > maxLen)
                maxLen = queue.size();
        }
        
        return maxLen;
    }
}


public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }
}
