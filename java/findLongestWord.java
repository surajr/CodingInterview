class Solution {
    public String findLongestWord(String s, List<String> d) {
        
        String result = "";
        for(String word: d)
        {
            int i=0;
            for(char c: s.toCharArray())
                if(i<word.length() && word.charAt(i) == c)
                    i++;
            
            if(i == word.length() && (word.length() >= result.length()))
                if(word.length() > result.length() || word.compareTo(result)<0)
                    result = word;
        }
        return result;
    }
}
