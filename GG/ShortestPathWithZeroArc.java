
/*
 *  给一个undirected graph，举个栗子如下：
0-(0)--1--(1)--2
\        \
  (1)    (1)
    \        \
     3-(0)--4. 
给两个数字，代表起点和终点，请问最短的从起点到终点的路径，且最多经过一个（0）的arc
 */
public class ShortestPathWithArc {
	// run time: O(V+E)
	private class Node
	{
		int id;
		// neighbor node as key, edge value as value, can be 0 or 1
		HashMap<Node, Integer> neighbors = new HashMap<>();
		boolean traversedZero = false; // whether the path to the node has traversed zero
		Node prev; // used to record path and see if path has traversed zero
		public Node(int id)
		{
			this.id = id;
		}
	}
	
	public List<Node> getShortestPath(List<Node> graph, int start, int end)
	{
		Node startNode = null;
		for (Node node: graph)
		{
			if (node.id == start)
			{
				startNode = node;
				break;
			}
		}
		Node endNode = null;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(startNode);
		
		while (!queue.isEmpty())
		{
			Node current = queue.poll();
			if (current.id == end)
			{
				// found end node
				endNode = current;
				break;
			}
			for (Map.Entry<Node, Integer> entry: current.neighbors.entrySet())
			{
				Node next = entry.getKey();
				int edgeVal = entry.getValue();
				// if first time to the node or the next node has traversed zero previously but path from current node doesn't
				// have zero edge, enabling the next node to search with zero edge again
				if (next.prev == null || (next.traversedZero && !current.traversedZero && edgeVal == 1))
				{
					// set prev
					next.prev = current;
					next.traversedZero = (next.prev == null) ? (current.traversedZero || edgeVal == 0): false;
					queue.offer(next);
				}
			}
		}
		
		List<Node> path = new ArrayList<Node>();
		Node tmp = endNode;
		while (tmp != null)
		{
			path.add(0, tmp);
			tmp = tmp.prev;
		}
		return path;
	}
	
	@Test
	public void test() {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		Node d = new Node(4);
		Node e = new Node(5);
		Node f = new Node(6);
		a.neighbors.put(c, 1);
		a.neighbors.put(e, 1);
		c.neighbors.put(a, 1);
		c.neighbors.put(b, 0);
		e.neighbors.put(a, 1);
		b.neighbors.put(c, 0);
		b.neighbors.put(e, 1);
		b.neighbors.put(d, 0);
		d.neighbors.put(b, 0);
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(a);
		nodes.add(b);
		nodes.add(c);
		nodes.add(d);
		nodes.add(e);
		nodes.add(f);
		System.out.println(getShortestPath(nodes, 1, 4));
	}
}
