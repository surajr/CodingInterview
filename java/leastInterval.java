class Solution {
    public int leastInterval(char[] tasks, int n) {
        
        int[] map = new int[26];
        
        for(char task : tasks)
            map[task - 'A']++;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        
        for(int entry : map)
            if (entry > 0)
                queue.offer(entry);
        
        int time = 0;
        
        while(!queue.isEmpty())
        {
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            while(i <= n)
            {                
                if(!queue.isEmpty())
                {
                    if(queue.peek()>1)
                    {
                        temp.add(queue.poll()-1);
                    }
                    else
                        queue.poll();
                    
                    
                }
                time++;
                if(queue.isEmpty() && temp.size()==0)
                    break;
                i++;
            }
            
            for(int a : temp)
                queue.offer(a);
        }
        
        return time;       
        
    }
}
