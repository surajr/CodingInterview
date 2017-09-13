//Divide and Conquer method

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }
    
    private ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];
        int mid = lo + (hi - lo) / 2;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);
        return merge(l1, l2);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }
}

// Priority Queue

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        ListNode dummy = new ListNode(0), cur = dummy;
        
        if(lists.length == 0)
            return null;
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2)
            {
                return l1.val - l2.val;
            }
        });
        
        for(ListNode list : lists)
            if(list != null)
                minHeap.offer(list);
        
        while(!minHeap.isEmpty())
        {
            ListNode temp = minHeap.poll();
            cur.next = temp;
            if(temp.next != null)
                minHeap.offer(temp.next);      
            
            cur = temp;
        }
        
        return dummy.next;        
        
    }
}
