package Palantir;
import java.util.*;
import org.junit.Test;
import org.junit.Assert;

public class GridTree {
	public void BuildOverlay(Node a, Node b, Node c)
    {
        if(a == null && b == null) {
        	return;
        }
        if(a.color != null && b.color != null) 
        {
        	c = new Node((a.color == 'W' && b.color == 'W') ? 'W' : 'B', null);
        }
        else if(a.color != null && b.color == null)
        {
            if  (a.color == 'B') {
            	c = new Node('B', null);
            }
            else                 
            {
            	c = b.Clone(); // deep copy of B, skip the code..
            }
        }
        else if(a.color == null && b.color != null)
        {
            if  (b.color == 'B') {
            	c = new Node('B', null);
            }
            else {
            	c = a.Clone(); // deep copy of A, skip the code..
            }
        }
        else
        {
            // a.color == '' && b.color == '' : both has chilren.
            // if we draw a graph we can conclude that they should have equal number of childs.
            Node child = null;
            c = new Node(null, new ArrayList<Node>());
            for(int i = 0; i < a.childs.size(); i++)
            {
                BuildOverlay(a[i], b[i], child);
                if(child != null) c.childs.add(child);
            }
            
            // exam if we need to combine childs 
            boolean flag = false;
            for(int i = 0; i < c.childs.size() - 1; i++)
            {
                // if any child has more children, or any of the two have different color, set flag to true
                flag |= (c.childs.get(i).color == null) || (c.childs.get(i+1).color == null) || (c.childs.get(i).color != childs[i+1].color);
            }
            
            if(!flag) 
            { 
            	c.color = c.childs.get(0).color; c.childs = null;
        	}
        }
    }
    
    public class Node
    {
        public Character color;
        public List<Node> childs;
        public Node(Character color, List<Node> children)
        {
        	this.color = color;
        	this.childs = children;
        }
    }
	@Test
	public void test() {
	}
}
