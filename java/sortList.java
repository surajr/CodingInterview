/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
Each time divided the given list into two sub list. Merge sub list after bottom case return.
 */
class Solution {
    public ListNode sortList(ListNode head) {
        
        if(head == null) return null;
        if(head.next == null) return head;
        
        ListNode pre = head, p1 = head, p2 = head;
        
        while(p2 != null && p2.next != null)
        {
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        pre.next = null;
        
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(p1);
        
        return merge(head1, head2);        
    }
    
    public ListNode merge(ListNode p1, ListNode p2)
    {
        if(p1 == null) return p2;
        if(p2 == null) return p1;
        
        if(p1.val < p2.val)
        {
            p1.next = merge(p1.next, p2);
            return p1;
        }
        else
        {
            p2.next = merge(p1, p2.next);
            return p2;
        }      
        
    }
}
