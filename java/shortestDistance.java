class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int ret = Integer.MAX_VALUE, index1 = -1, index2 = -1;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                index1 = i; 
                if(index2 >= 0) ret = Math.min(ret, i - index2);
            } else if(words[i].equals(word2)) {
                index2 = i;
                if(index1 >= 0) ret = Math.min(ret, i - index1);
            }
        }
    return ret;
    }
}
