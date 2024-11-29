public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      public static ListNode of(int[] vals) {
            ListNode head = new ListNode();
            ListNode cur = head;
            for (int val : vals) {
                  ListNode listNode = new ListNode(val);
                  head.next = listNode;
                  head = listNode;
            }
            return cur.next;
      }
}