class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        
        List<Integer>[] bucket = new List[nums.length+1];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int num:nums)
            map.put(num, map.getOrDefault(num,0)+1);
        
        for(int key:map.keySet())
        {
            int freq = map.get(key);
            if(bucket[freq] == null)
                bucket[freq] = new LinkedList<>();
            bucket[freq].add(key);
        }
        
        List<Integer> result = new LinkedList<>();
        for(int pos = bucket.length-1; pos >= 0 && result.size() < k; pos--)
        {
            if(bucket[pos] != null)
                result.addAll(bucket[pos]);
        }
        return result;
    }
}
