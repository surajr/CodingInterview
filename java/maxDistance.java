class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        
        int res = 0, minVal = arrays.get(0).get(0), maxVal = arrays.get(0).get(arrays.get(0).size()-1);
        for(int i=1; i<arrays.size(); i++)
        {
            res = Math.max(res, Math.max(Math.abs(maxVal - arrays.get(i).get(0)), Math.abs(arrays.get(i).get(arrays.get(i).size()-1)-minVal)));
            minVal = Math.min(minVal, arrays.get(i).get(0));
            maxVal = Math.max(maxVal, arrays.get(i).get(arrays.get(i).size()-1));
        }
        return res;
    }
}
