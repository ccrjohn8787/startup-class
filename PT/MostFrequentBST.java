package Palantir;

import java.util.Stack;

public class MostFrequentInBST {
    static int mostFrequentCount = 0;
    static int mostFrequentValue = 0;
    static int currFreq = 0;
    static int currVal = 0;

    public int getMostFrequent() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(12);
        root.right.left = new TreeNode(12);
        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(12);
        root.right.right = new TreeNode(12);
        root.right.right.left = new TreeNode(12);
        root.right.right.right = new TreeNode(12);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(6);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(6);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(6);
        root.left.right.right.right = new TreeNode(7);
        currVal = root.val;
        helper(root);
        return mostFrequentValue;
    }

    public void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (currVal == root.val) {
            currFreq ++;
        } else {
            currVal = root.val;
            currFreq = 1;
        }
        if (currFreq > mostFrequentCount) {
            mostFrequentCount = currFreq;
            mostFrequentValue = currVal;
        }
        helper(root.right);
    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode (int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public class ReturnType {
        public int currFreq, currValue;
        public ReturnType (int currFreq, int currValue) {
            this.currFreq = currFreq;
            this.currValue = currValue;
        }
    }

    public int getKthElement (TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        int count = 0;
        int result = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty() && count < k) {
            TreeNode curr = stack.pop();
            result = curr.val;
            count++;

            curr = curr.right;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }

        if (count == k) {
            return result;
        } else {
            return -1;
        }
    }
}
