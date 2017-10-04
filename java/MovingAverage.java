class MovingAverage {
    
    Queue<Integer> queue;
    double sum = 0;int size;

    /** Initialize your data structure here. */
    public MovingAverage(int s) {
        size = s;
        queue = new LinkedList<>();
    }
    
    public double next(int val) {
        if(queue.size() == size)
            sum = sum - queue.poll();
        
        queue.offer(val);
        sum += val;
        return sum/queue.size();
        
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
