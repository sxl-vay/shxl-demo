package tree;

import POJO.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeBuilder {
    public static void main(String[] args) {
        TreeNode treeNode = buildTree(new Integer[]{1, 2, null, null, 5, 4});
        System.out.println(treeNode);
    }
    public static TreeNode buildTree(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (i < array.length) {
            TreeNode current = queue.poll();
            if (current == null) continue;

            if (i < array.length && array[i] != null) {
                current.left = new TreeNode(array[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < array.length && array[i] != null) {
                current.right = new TreeNode(array[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

}
