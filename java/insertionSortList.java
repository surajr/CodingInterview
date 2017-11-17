/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {

        ListNode helper = new ListNode(-1);
        ListNode prev = helper;
        ListNode cur = head;
        
        while(cur != null)
        {
            prev = helper;
            
            while(prev.next != null && prev.next.val < cur.val)
                prev = prev.next;
            
            ListNode temp = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = temp;            
        }
        
        return helper.next;
    }
}
