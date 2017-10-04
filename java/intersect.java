class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        List <Integer> s = new ArrayList<>();
        while(p1 < nums1.length && p2 < nums2.length){
            if (nums1[p1] == nums2[p2]){
                s.add(nums1[p1]);
                p1 ++;
                p2 ++;
            }
                
                
            else if (nums1[p1] < nums2[p2])
                p1 ++;
            else 
                p2 ++;
        }
        int [] r = new int[s.size()];
        for (int i =0; i < s.size(); i++){
            r[i] = s.get(i);
        }
        return r;
    }
}

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < nums1.length; i++)
        {
            if(map.containsKey(nums1[i])) map.put(nums1[i], map.get(nums1[i])+1);
            else map.put(nums1[i], 1);
        }
    
        for(int i = 0; i < nums2.length; i++)
        {
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0)
            {
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i])-1);
            }
        }
    
       int[] r = new int[result.size()];
       for(int i = 0; i < result.size(); i++)
       {
           r[i] = result.get(i);
       }
    
       return r;
    }
}
