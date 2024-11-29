public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode = ListNode.of(new int[]{1, 2, 3, 4, 5});
        ListNode listNode1 = solution.removeNthFromEnd(listNode, 2);

    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode a = head;
        ListNode t = head;
        while(t.next != null){
            if(n>0){
                n--;
            } else {
                a = a.next;
            }
            t = t.next;
        }
        if(a.next!= null){
            a= a.next.next;
        }
        return head;
    }
}