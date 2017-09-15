class Solution {
    public int findLongestChain(int[][] pairs) {
        int end = Integer.MIN_VALUE, count = 0;
        
        Arrays.sort(pairs, (p1, p2) -> p1[1]-p2[1]);
        
        for(int [] pair : pairs)
        {
            if(pair[0]>end)
            {
                count++;
                end = pair[1];
            }
        }
        
        return count;       
    }
}
