class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        for(char c:S.toCharArray()){
            if(c == '(')
                stack.push(-1);
            else{
                int cur = 0;
                while(stack.peek() != -1)
                    cur += stack.pop();
                stack.pop();
                stack.push(cur ==0 ? 1:2*cur);
            }
        }
        int sum = 0;
        while(!stack.isEmpty())
            sum += stack.pop();
        return sum;
    }
}
