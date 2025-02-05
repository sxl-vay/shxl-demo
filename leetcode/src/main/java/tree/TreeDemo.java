package tree;

import POJO.TreeNode;

import java.util.Stack;

public class TreeDemo {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        traverse(treeNode);

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Integer peek = stack.peek();
        System.out.println("peek = " + peek);
        Integer pop = stack.pop();
        System.out.println("pop = " + pop);
    }

    public static void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        System.out.println("root = " + root.val);
        traverse(root.right);
    }
}
