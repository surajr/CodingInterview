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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        
        for(Interval iter:intervals)
        {
            if(newInterval == null || iter.end < newInterval.start)
                result.add(iter);
            else if(iter.start > newInterval.end)
            {
                result.add(newInterval);
                result.add(iter);
                newInterval = null;
            }
            else
            {
                newInterval.start = Math.min(newInterval.start, iter.start);
                newInterval.end = Math.max(newInterval.end, iter.end);
            }
        }
        if(newInterval != null)
            result.add(newInterval);
        
        return result;
    }
}
