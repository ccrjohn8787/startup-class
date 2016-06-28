package Google;

import org.junit.Test;

/**
 * Created by siyuzhan on 5/8/16.
 */
public class BinaryTreeLCALCP {

    public TreeNode getLowestNode(TreeNode root) {
        return getLowestNodeHelper(root).lowest;
    }

    public ReturnType getLowestNodeHelper(TreeNode root) {
        if (root == null) {
            return new ReturnType(null, 0);
        }
        if (root.left == null && root.right == null) {
            return new ReturnType(root, 1);
        }
        ReturnType left = getLowestNodeHelper(root.left);
        ReturnType right = getLowestNodeHelper(root.right);
        if (left.height > right.height) {
            return new ReturnType(left.lowest, left.height + 1);
        } else {
            return new ReturnType(right.lowest, right.height + 1);
        }
    }

    public class ReturnType {
        TreeNode lowest;
        int height;
        public ReturnType(TreeNode node, int height) {
            this.height = height;
            this.lowest = node;
        }
    }

    // 这里有点tricky的是LCP和LCA的区别，假如p,q 是BT中的两个node，p是q的ancestor，那么LCA是p， LCP是p的parent

    public TreeNode getLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = getLCA(root.left, p, q);
        TreeNode right = getLCA(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    public TreeNode getLCP(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // !!!IMPORTANT!!! Only difference from LCA - if child is p or q, return root
        if (root.left == p || root.right == q || root.right == p || root.left == q) {
            return root;
        }
        TreeNode left = getLCP(root.left, p, q);
        TreeNode right = getLCP(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    public class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(8);
        root.left = node1;
        root.right = node6;
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node4.left = node5;
        node6.left = node7;
        System.out.println(getLowestNode(root).val);
        System.out.println(getLCA(root, node2, node5).val);
        System.out.println(getLCP(root, node3, node4).val);

    }
}
