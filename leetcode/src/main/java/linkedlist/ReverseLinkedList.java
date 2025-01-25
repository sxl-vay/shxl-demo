package linkedlist;

import POJO.ListNode;

import java.util.List;

public class ReverseLinkedList {
    public List<ListNode> reverseList(ListNode head, int n) {
        if (head == null || head.next == null) return List.of(head,head);
        ListNode next = null;
        ListNode reverse = null;
        ListNode cur = head;
        for (int i = 0; i < n; i++) {
            next = cur.next;
            cur.next = reverse;
            reverse = cur;
            cur = next;
        }
        head.next = next;
        //
        return List.of(reverse,next);
    }

}
