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
         ListNode dummy = new ListNode(-1);
         dummy.next = head;
         ListNode cur = dummy;

         while(cur.next != null && cur.next.next != null){
             ListNode first = cur.next;
             ListNode second = cur.next.next;
             first.next = second.next;
             second.next = cur.next;
             cur.next = second;
             cur = cur.next.next;
         }
         return dummy.next;
     }
 }
