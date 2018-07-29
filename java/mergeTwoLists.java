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







/* better way to solve this */



class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}
