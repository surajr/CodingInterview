class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int [] table = new int[128];
        
        for(char c : magazine.toCharArray())
            table[c]++;
        
        for(char c : ransomNote.toCharArray())
            if(--table[c] < 0)
                return false;
        
        return true;
    }
}
