package Google;
import java.util.*;
import org.junit.Test;
import org.junit.Assert;
/*
 * 题目是给你一个board，里面存储user的信息，user有id和socre。
board有adduser(id, score)(返回add进去的user当前的rank), findByRank(k) (这个返回id)。
Add如果本身已经有id在board中，需要对这个id的score进行update。(BST insert, delete)
 */
public class FindByRank {
	// BST approach: O(log(n)) time insert and O(log(n)) time find 
    public class TreeNode {
        User user;
        int count; // number of nodes in subtree
        TreeNode left, right;
        public TreeNode(User user) {
            this.user = user;
            this.count = 1;
            this.left = this.right = null;
        }
    }

    public class User {
        int id, score;
        public User (int id, int score) {
            this.id = id;
            this.score = score;
        }
    }
	
    public class Board {
        TreeNode root;
        HashMap<Integer, User> map;
        public Board() {
            this.root = null;
            this.map = new HashMap<>();
        }

        // insert node to tree
        int addUser(int id, int score) {
            if (root == null) {
                root = new TreeNode(new User(id, score));
                return 1;
            }
            if (map.containsKey(id)) {
            	// TODO: Need to first delete from the tree (need to update count too) and then add user again
                //return updateUser(id, score);
            }
            // pre is parent node
            TreeNode pre = null;
            int rank = 1;
            TreeNode head = root;
            while (head != null) {
                pre = head;
                head.count++;
                if (head.user.score < score) {
                    rank += head.left == null ? 1 : head.left.count + 1;
                    head = head.right;
                } else {
                    head = head.left;
                }
            }
            TreeNode curr = new TreeNode(new User(id, score));
            if (pre.user.score < score) {
                pre.right = curr;
            } else {
                pre.left = curr;
            }
            return rank;
        }

        int findByRank(int k) {
            return findByRankHelper(root, k);
        }

        private int findByRankHelper(TreeNode root, int k) {
            if (root == null) {
                return -1;
            }
            // stop when count of left subtree is k-1
            if ((root.left == null && k == 1) || (root.left != null && root.left.count == k - 1)) {
                return root.user.id;
            }

            if (root.left == null || root.left.count < k) {
                return findByRankHelper(root.right, k - (root.left == null ? 0 : root.left.count) - 1);
            } else {
                return findByRankHelper(root.left, k);
            }
        }
    }
    
    @Test
    public void test() {
        Board board = new Board();
        System.out.println(board.addUser(1, 10));
        System.out.println(board.addUser(5, 50));
        System.out.println(board.addUser(2, 20));
        System.out.println(board.addUser(4, 40));
        System.out.println(board.addUser(3, 30));
        System.out.println(board.findByRank(3));
        System.out.println(board.findByRank(2));
        System.out.println(board.findByRank(4));
        System.out.println(board.findByRank(1));
        System.out.println(board.findByRank(5));
    }
}

