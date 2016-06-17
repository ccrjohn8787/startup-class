package Google;
import java.util.*;

import org.junit.Test;
import org.junit.Assert;

/*
 * 假设有个social network
( 1）返回朋友的朋友，但不是自己的朋友；
( 2 ) 返回朋友的朋友，担不是自己的朋友，按照出现的次数排序，也就是说加入一个人不是我的朋友，但是他是我所有的朋友的朋友，那么他应该是返回结果里面的第一个
 */
public class FriendOfFriend {
	// idea: (1) friend of friend: level order traversal, second level
	//       (2) each second level friend add a count, return sorted second level friends
	public class Node
	{
		int id;
		HashSet<Node> friends = new HashSet<>();
		int count; // count of 1 degree friend for 2 degree node
		int degree;
		public Node(int id)
		{
			this.id = id;
		}
		
		@Override
		public String toString()
		{
			return id + " " + count;
		}
	}
	public List<Node> getFriendofFriend(Node start)
	{
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(start);
		HashSet<Integer> set = new HashSet<>();
		set.add(start.id);
		int degree = 0;
		List<Node> result = new ArrayList<Node>();
		while (!queue.isEmpty())
		{
			int size = queue.size();
			degree++;
			for (int i = 0; i < size; i++)
			{
				Node node = queue.poll();
				for (Node next: node.friends)
				{
					if (set.contains(next.id))
					{
						if (next.degree <= 1)
						{
							// start or friend of start
							continue;
						}
						else
						{
							next.count++;
						}
					}
					else
					{
						if (degree == 2)
						{
							next.count++;
							result.add(next);
						}
						next.degree = degree;
						set.add(next.id);
						queue.offer(next);
					}
				}
			}
			if (degree == 2)
			{
				// stop on second level
				break;
			}
		}
		Collections.sort(result, new Comparator<Node>(){
			public int compare(Node n1, Node n2)
			{
				return n2.count - n1.count;
			}
		});
		return result;
	}
	@Test
	public void test() {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		Node d = new Node(4);
		Node e = new Node(5);
		a.friends.add(b);
		a.friends.add(c);
		b.friends.add(a);
		b.friends.add(c);
		b.friends.add(d);
		c.friends.add(a);
		c.friends.add(d);
		c.friends.add(e);
		e.friends.add(c);
		d.friends.add(b);
		d.friends.add(c);
		System.out.println(getFriendofFriend(a));
	}
}
