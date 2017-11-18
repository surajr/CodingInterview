ass Solution {
    public List<String> generateParenthesis(int n) {
        
        List<String> result = new ArrayList<>();
        if(n == 0) return result;
        helper("", result, n, n);
        return result;        
    }
    
    public void helper(String sublist, List<String> list, int left, int right)
    {
        if(left > right)
            return;
        
        if(left > 0)
            helper(sublist+"(", list, left-1, right);
        if(right > 0)
            helper(sublist+")", list, left, right-1);
        if(left == 0 && right == 0)
            list.add(sublist);
        return;
    }
}
