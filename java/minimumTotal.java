class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int [] result = new int[size+1];
        
        for(int i=size-1; i>=0; i--)
        {
            List<Integer> temp = triangle.get(i);
            
            for(int j=0; j<temp.size(); j++)
                result[j] = Math.min(result[j], result[j+1]) + temp.get(j);
        }
        return result[0];
    }
}
