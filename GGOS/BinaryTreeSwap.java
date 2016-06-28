package Google;/**
 * Created by siyuzhan on 6/12/16.
 */

import java.util.*;

import org.junit.Test;

public class BinaryTreeSwap {
    /**
     * 问一棵二叉树能不能通过 swap 左右子树变成另一颗树
     */

    public boolean canSwap(TreeNode a, TreeNode b) {
        if (a == null) {
            return b == null;
        }
        if (b == null) {
            return a == null;
        }
        if (a.val == b.val) {
            return (canSwap(a.left, b.left) && canSwap(a.right, b.right)) ||
                    (canSwap(a.right, b.left) && canSwap(a.left, b.right));
        }
        return false;
    }

    /**
     * 问一二叉棵树通过swap操作能不能变为二分查找树
     */
    public boolean canSwapToBST(TreeNode n) {
        return helper(n).isValid;
    }

    public ReturnType helper(TreeNode n) {
        if (n == null) {
            return null;
        }

        ReturnType l = helper(n.left);
        ReturnType r = helper(n.right);
        if (l == null && r == null) {
            return new ReturnType(true, n.val, n.val);
        } else if (l == null) {
            if (r.isValid && (n.val < r.min || n.val > r.max)) {
                return new ReturnType(true, Math.min(r.min, n.val), Math.max(r.max, n.val));
            } else {
                return new ReturnType(false, -1, -1);
            }
        } else if (r == null) {
            if (l.isValid && (n.val < l.min || n.val > l.max)) {
                return new ReturnType(true, Math.min(l.min, n.val), Math.max(l.max, n.val));
            } else {
                return new ReturnType(false, -1, -1);
            }
        } else {
            // both are not null
            if (l.isValid && r.isValid &&
                    (l.max < n.val && n.val < r.min) ||
                    (r.max < n.val && n.val < l.min)) {
                return new ReturnType(true, Math.max(l.min, r.min), Math.max(l.max, r.max));
            } else {
                return new ReturnType(false, -1, -1);
            }
        }
    }

    public class ReturnType {
        boolean isValid;
        int min, max;
        public ReturnType(boolean isValid, int min, int max) {
            this.isValid = isValid;
            this.min = min;
            this.max = max;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode (int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(1);
        root.right = n1;
        root.left = n2;
        n2.right = n3;
        System.out.println(canSwapToBST(root));
    }
}
