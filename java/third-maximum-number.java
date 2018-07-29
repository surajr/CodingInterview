/*

start with a naive (brute force) approach, we can have three max variables and keep track of which is max and which is min as mentioned below.
But this is not scalable because "If interviewer asks for 50th maximum number, then number of if-else will grow exponentially. lol"
So, we can create a PriorityQueue of size N and keep inserting numbers by checking the root of the PQ.

*/

class Solution {
    public int thirdMax(int[] nums) {
        Integer max1=null, max2=null, max3=null;
        for(Integer n : nums){
            if(n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
            if(max1 == null || n > max1){
                max3 = max2;
                max2 = max1;
                max1 = n;
            }
            else if(max2 == null || n > max2){
                max3 = max2;
                max2 = n;
            }
            else if(max3 == null || n > max3){
                max3 = n;
            }
        }
        return max3 == null? max1:max3;
    }
}

class Solution {
    private Integer WHICH_MAX = 3;
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Integer n:nums ){
            if(! pq.contains(n) ) {
                pq.offer(n);
                if(pq.size() > WHICH_MAX) pq.poll(); 
            }
        }
        if(pq.size() < WHICH_MAX){
            while(pq.size()>1)
                pq.poll();
        }
        return pq.peek();
    }
}
