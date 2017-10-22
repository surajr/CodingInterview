/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode first = new ListNode(-1);
        first.next = head;
        
        ListNode pre = first, cur = head;
        
        while(cur != null && cur.next != null)
        {
            pre.next = cur.next;
            pre = cur;
            cur = cur.next.next;
            pre.next.next = pre;
        }
        pre.next = cur;
        return first.next;
    }
}
