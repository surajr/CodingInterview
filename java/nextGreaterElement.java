public class Solution 
{
    public int[] nextGreaterElement(int[] findNums, int[] nums) 
    {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int num : nums)
        {
            while(!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(),num);
            stack.push(num);
        }
        
        for(int i=0; i<findNums.length; i++)
            findNums[i] = map.getOrDefault(findNums[i],-1);
        
        return findNums;
    }   
}
