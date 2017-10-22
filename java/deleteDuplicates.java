/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
        if(head == null || head.next == null)
            return head;
        
        ListNode p1 = head, p2 = head.next;
        
        while(p2 != null)
        {
            if(p1.val == p2.val)
            {
                p2 = p2.next;
                p1.next = p2;
            }
            else
            {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return head;
    }
}





class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode cur = head;        
        while(cur != null)
        {
            if(cur.next == null)
                return head;
            if(cur.val == cur.next.val)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head; 
        
    }
}


