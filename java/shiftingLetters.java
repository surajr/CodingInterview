class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        if(shifts.length == 0) return S;
        char [] arr = S.toCharArray();
        int shift = 0;
        for(int i=shifts.length-1; i>=0; i--){
            shift = (shift + shifts[i])%26;
            arr[i] = (char)((arr[i] - 'a' + shift) % 26 + 'a');
        }
        return new String(arr);
    }
}
