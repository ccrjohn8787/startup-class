public class Solution {
    TreeNode root = null;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        root = new TreeNode(nums[nums.length-1]);
        result.add(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            // insert returns the number of elements smaller than num
            result.add(0, insert(root, nums[i]));
        }
        return result;
    }
    
    public int insert(TreeNode root, int num) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        // need prev since root goes all the way to null and prev points to the parent 
        // node of the node we would insert
        TreeNode prev = null;
        while (root != null) {
            // bst equal number on the left
            if (num <= root.val) {
                // increment root count since we are going to insert a node later
                root.count++;
                prev = root;
                root = root.left;
            } else if (num > root.val) {
                // aggregate count since all nodes on 
                count += root.count;
                prev = root;
                root = root.right;
            }
        }
        if (num <= prev.val) {
            prev.left = new TreeNode(num);
        } else {
            prev.right = new TreeNode(num);
        }
        return count;
    }
    
    public class TreeNode {
        // count is total number of nodes below the node, inclusive
        int val, count;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            // by default has count of 1
            this.count = 1;
            this.left = this.right = null;
        }
    }
}
