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
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;
        
        Arrays.sort(intervals, (a,b) -> (a.start - b.start));
        PriorityQueue<Interval> pq = new PriorityQueue<>(intervals.length, (a,b)->(a.end-b.end));
        
        for(Interval interval:intervals)
        {
            if(!pq.isEmpty() && interval.start >= pq.peek().end)
                pq.poll();
            pq.offer(interval);
        }
        return pq.size();
    }
}
