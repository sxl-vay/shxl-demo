package linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
      public int val;
      public ListNode next;
      public ListNode() {}
      public ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      public static ListNode of(int... vals) {
            ListNode head = new ListNode();
            ListNode cur = head;
            for (int val : vals) {
                  ListNode listNode = new ListNode(val);
                  head.next = listNode;
                  head = listNode;
            }
            return cur.next;
      }

      public void printList() {
            ListNode cur = this;
            List<Integer> list = new ArrayList<>();
            while (cur != null) {
                  list.add(cur.val);
                  cur = cur.next;
            }
            System.out.println("list = " + list);
      }

      public static void print(ListNode listNode1) {
            List<Integer> list = new ArrayList<>();
            while (listNode1 != null) {
                  list.add(listNode1.val);
                  listNode1 = listNode1.next;
            }
            System.out.println("list = " + list);
      }
}