class MyStack {

    /** Initialize your data structure here. */
    
    Queue<Integer> queue;
    public MyStack() {
        queue = new LinkedList<>();        
    }
    
    /** Push element x onto stack. */
    
    /*
        Idea: Rotate the queue to become stack    
    */
    
    public void push(int x) {
        queue.offer(x);
        for(int i=0; i< queue.size()-1;i++)
            queue.offer(queue.poll());
        
    }
    
    /** Removes the element on top of the stack and returns that element. */
    
    /*
        Idea: Remove the top element    
    */
    
    
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    
    
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
