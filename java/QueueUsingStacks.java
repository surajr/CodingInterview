class MyQueue {

    Stack<Integer> stack;
    Stack<Integer> queue;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        queue = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(queue.isEmpty())
        {
            while(!stack.isEmpty())
                queue.push(stack.pop());
        }
        return queue.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(queue.isEmpty())
        {
            while(!stack.isEmpty())
                queue.push(stack.pop());
        }
        return queue.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty() && queue.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
