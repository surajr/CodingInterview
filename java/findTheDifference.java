class Solution {
    public char findTheDifference(String s, String t) {
        char result = 0;
        for (char x : s.toCharArray()) result ^= x;
        for (char x : t.toCharArray()) result ^= x;
        return result;
    }
}
