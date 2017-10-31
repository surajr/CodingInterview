/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        
        if(intervals.size() == 0)
            return result;
        
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval obj0, Interval obj1)
            {
                return obj0.start - obj1.start;
            }
        });
        
        
        Interval iter = intervals.get(0);
        
        for(Interval cur:intervals)
        {
            if(cur.start <= iter.end)
                iter.end = Math.max(cur.end, iter.end);
            else
            {
                result.add(iter);
                iter = cur;
            }                
        }
        result.add(iter);
        return result;
    }
}
