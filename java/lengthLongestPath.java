ass Solution {
    public int lengthLongestPath(String input) {
        
        if(input == null) return 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        
        int result = 0;
        
        for(String s:input.split("\n"))
        {
            int level = s.lastIndexOf("\t")+1;
            int len = s.length() - level;
            if(s.contains("."))
                result = Math.max(result, map.get(level)+len);
            else
                map.put(level+1, map.get(level)+len+1);
        }
        
        return result;
    }
}
