tion for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        ListNode result = new ListNode(-1);
        ListNode p3 = result;
        
        while(p1 != null || p2 != null)
        {
            if(p1 == null && p2 != null)
            {
                p3.next = p2;
                return result.next;
            }
            else if(p1 != null && p2 == null)
            {
                p3.next = p1;
                return result.next;
            }
            else
            {
                if(p1.val < p2.val)
                {
                    p3.next = new ListNode(p1.val);
                    p1 = p1.next;
                }                    
                else
                {
                    p3.next = new ListNode(p2.val);
                    p2 = p2.next;
                }               
            }
            p3 = p3.next;      
           
        }
        return result.next;
    }
}
