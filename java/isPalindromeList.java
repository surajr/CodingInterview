/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        if(fast != null) slow = slow.next;
        
        ListNode halfHead = reverse(slow);
        ListNode cur = head;
        
        while(halfHead != null)
        {
            if(halfHead.val != cur.val)
                return false;
            halfHead = halfHead.next;
            cur = cur.next;
        }
        return true;
    }
    
    public ListNode reverse(ListNode head)
    {
        ListNode cur = head, prev = null;
        while(cur != null)
        {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
