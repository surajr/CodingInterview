class MapSum {
    
    class TrieNode
    {
        Map<Character,  TrieNode> children;
        int value;
        boolean isWord;
        
        public TrieNode()
        {
            children = new HashMap<>();
            value = 0;
            isWord = false;
        }
    }

    TrieNode root;
    
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode cur = root;
        for(char c:key.toCharArray())
        {
            TrieNode next = cur.children.get(c);
            if(next == null)
            {
                next = new TrieNode();
                cur.children.put(c, next);
            }
            cur = next;            
        }
        cur.isWord = true;
        cur.value = val;
    }
    
    public int sum(String prefix) {
        TrieNode cur = root;
        for(char c:prefix.toCharArray())
        {
            TrieNode next = cur.children.get(c);
            if(next == null)
                return 0;
            cur = next;
        }
        return dfs(cur);
    }
    
    public int dfs(TrieNode cur)
    {
        int sum = 0;
        for(char c:cur.children.keySet())
            sum += dfs(cur.children.get(c));
        return sum+cur.value;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
