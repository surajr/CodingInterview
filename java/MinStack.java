// Solution 1 - Using one Stack

class MinStack {    
    private class Node
    {
        int val;
        int minVal;
        private Node(int val, int minVal)
        {
            this.val = val;
            this.minVal = minVal;
        }       
    }   

    Stack<Node> stack = new Stack<>();    
    /** initialize your data structure here. */
    public MinStack() {   }
    
    public void push(int x) {
        int min = (stack.isEmpty() ? x : Math.min(stack.peek().minVal, x));
        stack.push(new Node(x, min));
    }
    
    public void pop() {
        stack.pop();       
    }
    
    public int top() {
        return stack.peek().val;        
    }
    
    public int getMin() {
        return stack.peek().minVal;
    }
}

//Solution 2 Using two Stacks


class MinStack {
    
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> mini = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
        
        stack.push(x);        
        if(!mini.isEmpty())
        {
            
            if(x <= mini.peek())
                mini.push(x);
        }
        else
            mini.push(x);
        
    }
    
    public void pop() {
        int x = stack.pop();
        if(!mini.isEmpty())
        {
            if(x == mini.peek())
                mini.pop();
        }       
    }
    
    public int top() {
        return stack.peek();
        
    }
    
    public int getMin() {
        return mini.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
