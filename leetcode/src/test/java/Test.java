public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode = ListNode.of(new int[]{1, 2});
        ListNode listNode1 = solution.removeNthFromEnd(listNode, 2);
        ListNode.print(listNode1);
    }
}
