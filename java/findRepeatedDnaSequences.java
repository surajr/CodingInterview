class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> result = new HashSet<>();
        
        if(s.length() < 10) return new ArrayList<String>(result);
        
        for(int i=0; i<=s.length()-10; i++){
            String substr = s.substring(i, i+10);
            map.put(substr, map.getOrDefault(substr, 0)+1);
            if(map.get(substr)>1)
                result.add(substr);            
        }
                
        return new ArrayList<String>(result);
    }
}
