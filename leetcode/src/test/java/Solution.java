import POJO.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode bkA = headA;
        ListNode bkB = headB;
        while(headA!=headB){
            headA = headA ==null?bkB:headA.next;
            headB = headB == null?bkA:headB.next;
        }
        return headA;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode intersectionNode = solution.getIntersectionNode(ListNode.of(2, 6, 4), ListNode.of(1, 5));
        System.out.println(intersectionNode);
    }
}