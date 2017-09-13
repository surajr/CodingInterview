/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
    Create a new EvenHead node pointer. Link even node to evenHead and when we reach end of the List, join the oddTail with evenHead.
    
    Time O(n), Space O(1)
*/
class Solution {
    public ListNode oddEvenList(ListNode head) {
        
        if(head == null)
            return null;
        
        ListNode odd = head, even = head.next, evenHead = even;
        
        while(even != null && even.next != null)
        {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;            
        }
        
        odd.next = evenHead;
        
        return head;
        
    }
}
