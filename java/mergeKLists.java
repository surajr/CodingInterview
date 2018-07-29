//Divide and Conquer method

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length-1);
    }

    public ListNode merge(ListNode[] lists, int start, int end){
        if(start == end) return lists[start];
        else if(start < end){
            int mid = start + (end-start)/2;
            ListNode left = merge(lists, start, mid);
            ListNode right = merge(lists, mid+1, end);
            return mergeLists(left, right);
        }
        else
            return null;
    }

    public ListNode mergeLists(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        else if(l2 == null) return l1;
        else if(l1.val < l2.val){
            l1.next = mergeLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeLists(l1, l2.next);
            return l2;
        }
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
