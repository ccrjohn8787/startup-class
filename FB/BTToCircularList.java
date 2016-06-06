package Facebook;
import Google.Utils.*;
import Google.Utils;

import java.util.*;
import org.junit.Test;

/*
 * Binary tree to circular doubly linked list
 */
public class BinaryTreeToCircularLinkedList {


/*--------------------------- start of converting to doubly list node ---------------*/
	public class DoublyListNode
	{
		public int val;
		public DoublyListNode next, prev;
		public DoublyListNode(int val)
		{
			this.val = val;
		}
	}
	
	/*
	 * Convert to actual doubly linked list
	 * Approach 1: divide and conquer and use result type to indicate first and last
	 */
    private class ResultType
    {
        DoublyListNode first;
        DoublyListNode last;
        public ResultType(DoublyListNode first, DoublyListNode last)
        {
            this.first = first;
            this.last = last;
        }
    }
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        if (root == null)
        {
            return null;
        }
        ResultType t = helper2(root);
        t.first.next = t.last;
        t.last.prev = t.first;
        return t.first;
    }
    
    private ResultType helper2(TreeNode root)
    {
        if (root == null)
        {
            return new ResultType(null, null);
        }
        ResultType left = helper2(root.left);
        ResultType right = helper2(root.right);
        DoublyListNode node = new DoublyListNode(root.val);
        if (left.first == null)
        {
            left.first = node;
        }
        if (left.last != null)
        {
            left.last.next = node;
            node.prev = left.last;
        }
        if (right.last == null)
        {
            right.last = node;
        }
        if (right.first != null)
        {
            right.first.prev = node;
            node.next = right.first;
        }
        return new ResultType(left.first, right.last);
    }
	
	/*
	 * Convert to doubly list node
	 * Approach 2, not use result type. But would take longer time
	 * 
	 * Use recursion to do in order traversal and construct node on the way. Connect head and tail at the end
	 */
	public DoublyListNode serialize(TreeNode root)
	{
		DoublyListNode rootNode = helper(root);
		// get head
		DoublyListNode head = rootNode;
		while (head.prev != null)
		{
			head = head.prev;
		}
		// get tail
		DoublyListNode tail = rootNode;
		while (tail.next != null)
		{
			tail = tail.next;
		}
		head.prev = tail;
		tail.next = head;
		return head;
	}
	
	/*
	 * The returned node is the serialized doubly list node corresponding to root
	 */
	private DoublyListNode helper(TreeNode root)
	{
		if (root == null)
		{
			return null;
		}
		DoublyListNode left = helper(root.left);
		DoublyListNode node = new DoublyListNode(root.val);
		node.prev = left;
		if (left != null)
			left.next = node;
		DoublyListNode right = helper(root.right);
		node.next = right;
		if (right != null)
		{
			right.prev = node;
		}
		return node;
	}
	
	/*
	 * Convert to doubly list node
	 * Use stack iterative approach
	 */
    //stack
    public DoublyListNode convert(TreeNode root) {
        if (root == null) {return null;}
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (root.left != null) {
            stack.push(root.left);
            root = root.left;
        }

        DoublyListNode head = new DoublyListNode(stack.pop().val);
        DoublyListNode tail = head;
        if (root.left != null) {stack.push(root.left);}

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            tail.next = new DoublyListNode(node.val);
            tail.next.prev = tail;
            tail = tail.next;

            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        head.prev = tail;
        tail.next = head;
        return head;
    }
/*--------------------------- End of converting to doubly list node ---------------*/
	

/*--------------------------- Start of converting to treenode ---------------*/
	/*
	 * Approach 3: Iterative approach, not use doubly linked list but use treenode's left and right pointer
	 */
	public TreeNode BT2CircularDLL(TreeNode root) {
		if (root == null) {
			return root;
		}
		// use stack for inorder traversal
		Stack<TreeNode> st = new Stack<>();
		TreeNode curr = root;
		TreeNode head = null;
		TreeNode tail = null;
		while (curr != null || !st.isEmpty()) {
			if (curr != null) {
				st.push(curr);
				curr = curr.left;
			} else {
				TreeNode popped = st.pop();
				// tail's next is the popped node
				if (tail != null) {
					tail.right = popped;
					popped.left = tail;
				} else {
					// first node
					head = popped;
				}
				tail = popped;
				curr = popped.right;
			}
		}
		tail.right = head;
		head.left = tail;
		return head;
	}
	
	/*
	 * Another iterative approach to convert to treenode
	 */
    public TreeNode convert1(TreeNode root) {
        if (root == null) {return null;}
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (root.left != null) {
            stack.push(root.left);
            root = root.left;
        }

        TreeNode head = stack.pop();
        TreeNode tail = head;

        if (root.left != null) {stack.push(root.left);}

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            tail.right = node;
            tail.right.left = tail;
            tail = tail.right;

            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        head.left = tail;
        tail.right = head;
        return head;
    }
	
	/*
	 * Approach 4: recursive approach. convert to treenode but not linked list
	 */

    class ReturnType 
    {
    	TreeNode first, last;
    	public ReturnType(TreeNode first, TreeNode last)
    	{
    		this.first = first;
    		this.last = last;
    	}
    	public ReturnType(){}
    }
    public TreeNode convert2(TreeNode root) {
        if (root == null) {return root;}
        ReturnType convert = convert2Helper(root);
        convert.first.left = convert.last;
        convert.last.right = convert.first;
        return convert.first;
    }

    public ReturnType convert2Helper(TreeNode root) {
    	if (root == null)
    	{
    		return null;
    	}
        ReturnType left = convert2Helper(root.left);
        ReturnType right = convert2Helper(root.right);

        TreeNode head = left == null ? root : left.first;
        TreeNode tail = right == null ? root : right.last;

        if (left != null) {
            left.last.right = root;
            root.left = left.last;
        }
        if (right != null) {
            right.first.left = root;
            root.right = right.first;
        }
        return new ReturnType(head, tail);
    }
	
/*--------------------------- End of converting to tree node ---------------*/
	
	public class SolutionConvert {
	    public void solve(TreeNode root) {
	        if (root == null) {
	            return;
	        }
	        convertToDoubleLinkedList(root);           
	    }
	    
	    public void convertToDoubleLinkedList(TreeNode root) {
	        if (root == null) {
	            return;
	        }
	        if (root.left != null) {
	        	// convert left subtree
	            TreeNode left = root.left;
	            convertToDoubleLinkedList(left);
	            // traverse to the right most of left node and connect
	            // !! can optimize with a result type
	            while (left.right != null) {
	                left = left.right;
	            }
	            left.right = root;
	            root.left = left;
	        }
	        if (root.right != null) {
	        	// convert right subtree
	            TreeNode right = root.right;
	            convertToDoubleLinkedList(right);
	            while (right.left != null) {
	                right = right.left;
	            }
	            right.left = root;
	            root.right = right;
	        }
	    }
	}
	@Test
	public void test() {
		TreeNode root = Utils.deserialize("1,2,4,#,#,5,#,#,3,#,#");
		TreeNode node = convert2(root);
		System.out.println(node.val);
	}
}
