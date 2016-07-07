package Uber;
import java.util.*;
import org.junit.Test;

/*
 * Build a quad tree from an image represented as a matrix
 */
public class QuadTree {
	/*
	 * Use recursion to build each of the four children
	 */
	public class TreeNode
	{
		Integer val = null;
		TreeNode nw, ne, sw, se;
		public TreeNode(){};
		public TreeNode(int val){this.val = val;};
		
		@Override
		public String toString()
		{
			return "Node: " + (val == null ? "null" : val.toString());
		}
	}
	
	public TreeNode build(int[][] matrix)
	{
		return helper(matrix, 0, matrix.length-1, 0, matrix[0].length-1);
	}
	
	public TreeNode helper(int[][] matrix, int rowMin, int rowMax, int colMin, int colMax)
	{
		if (rowMin == rowMax && colMin == colMax)
		{
			return new TreeNode(matrix[rowMin][colMin]);
		}
		TreeNode ne = helper(matrix, rowMin, (rowMin + rowMax)/2, (colMin+colMax)/2 + 1, colMax);
		TreeNode se = helper(matrix, (rowMin + rowMax)/2+1, rowMax, (colMin+colMax)/2 + 1, colMax);
		TreeNode nw = helper(matrix, rowMin, (rowMin + rowMax)/2, colMin, (colMin+colMax)/2);
		TreeNode sw = helper(matrix, (rowMin + rowMax)/2+1, rowMax, colMin, (colMin+colMax)/2);
		if (isLeaf(ne) && isLeaf(se) && isLeaf(nw) && isLeaf(sw) && se.val == ne.val && nw.val == ne.val && sw.val == ne.val)
		{
			return new TreeNode(ne.val);
		}
		TreeNode node = new TreeNode();
		node.ne = ne;
		node.sw = sw;
		node.se = se;
		node.nw = nw;
		return node;
	}
	
	private boolean isLeaf(TreeNode node)
	{
		return node.val != null;
	}
	
	private void printTree(TreeNode root)
	{
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty())
		{
			int size = queue.size();
			for (int i = 0; i < size; i++)
			{
				TreeNode node = queue.poll();
				System.out.print(node + "    ");
				if (node.val == null)
				{
					queue.offer(node.nw);
					queue.offer(node.ne);
					queue.offer(node.se);
					queue.offer(node.sw);
				}
			}
			System.out.println();
		}
	}
	@Test
	public void test() {
		int[][] matrix = {{1,3,2,2},{2,2,2,2}, {2,3,1,1}, {4,5,1,1}};
		TreeNode node = build(matrix);
		printTree(node);
	}
}

package Palantir;
import java.util.*;
import org.junit.Test;

public class GridTree {
	public void BuildOverlay(Node a, Node b, Node c)
    {
        if(a == null && b == null) {
        	return;
        }
        if(a.color != null && b.color != null) 
        {
        	// If a and b both leaf node (with color), then overlay is also leaf
        	c = new Node((a.color == 'W' && b.color == 'W') ? 'W' : 'B');
        }
        else if(a.color != null && b.color == null)
        {
        	// a is leaf node, b is not (has neighbors)
            if  (a.color == 'B') {
            	// if there's a 'B', then overlay is colored with Black
            	c = new Node('B');
            }
            else                 
            {
            	c = b.deepCopy(); // deep copy of B, skip the code..
            }
        }
        else if(a.color == null && b.color != null)
        {
        	// b is leaf and a is not
            if  (b.color == 'B') {
            	c = new Node('B');
            }
            else {
            	c = a.deepCopy(); // deep copy of A, skip the code..
            }
        }
        else
        {
            // a.color == '' && b.color == '' : both has children.
            // if we draw a graph we can conclude that they should have equal number of children.
            Node child = null;
            c = new Node(null);
            for(int i = 0; i < a.childs.size(); i++)
            {
            	// build overlay on each of the child nodes
                BuildOverlay(a.childs.get(i), b.childs.get(i), child);
                if(child != null) {
                	c.childs.add(child);
                }
            }
            
            // examine if we need to combine childs 
            boolean shouldCombine = true;
            for(int i = 0; i < c.childs.size() - 1; i++)
            {
                // if any child has more children, or any of the two have different color, no need to combine
                if (c.childs.get(i).color == null || c.childs.get(i+1).color == null || c.childs.get(i).color != c.childs.get(i+1).color)
                {
                	shouldCombine = false;
                	break;
                }
            }
            
            if(shouldCombine) 
            { 
            	c.color = c.childs.get(0).color; 
            	c.childs = null;
        	}
        }
    }
    
    public class Node
    {
        public Character color = null;
        public List<Node> childs = new ArrayList<Node>();
        public Node(){};
        public Node(Character color)
        {
        	this.color = color;
        }
        
        public Node deepCopy()
        {
        	return helper(this);
        }
        
        private Node helper(Node root)
        {
        	if (root.color != null)
        	{
        		return new Node(root.color);
        	}
        	Node node = new Node();
        	for (int i = 0; i < root.childs.size(); i++)
        	{
        		node.childs.add(helper(root.childs.get(i)));
        	}
        	return node;
        }
    }
	@Test
	public void test() {
	}
}
