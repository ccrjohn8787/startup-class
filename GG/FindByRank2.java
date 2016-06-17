package Google;

import java.util.*;
import org.junit.Test;
import org.junit.Assert;

public class FindByRank2 {
	public class Node {
		public int score;
		public HashSet<String> IDSet;
		public int size;
		public Node left;
		public Node right;

		Node(String ID, int score) {
			IDSet = new HashSet<String>();
			IDSet.add(ID);
			this.score = score;
		}
	}

	public class BinarySearchTree {
		public HashMap<String, Node> scoreIDMap;
		Node root;

		BinarySearchTree() {
			scoreIDMap = new HashMap<String, Node>();
		}

		public void update(String ID, int score) {
			if (scoreIDMap.containsKey(ID)) {
				Node node = scoreIDMap.get(ID);
				if (node.IDSet.size() > 1) {
					node.IDSet.remove(ID);
				} else {
					delete(node);
				}
				Node newNode = new Node(ID, score);
				insert(newNode, ID, score);
				scoreIDMap.put(ID, newNode);
			}
		}

		public void insert(String ID, int score) {

		}

		public void insert(Node n, String ID, int score) {
			if (n == null) {
				n = new Node(ID, score);
			} else {
				if (n.score == score) {
					n.IDSet.add(ID);
				} else if (n.score > score) {
					n.size++;
					insert(n.left, ID, score);
				} else {
					insert(n.right, ID, score);
				}
			}
		}

		public void delete(Node node) {
			// find parent of node
			// check three case: leaf, one child, two children
			Node parent = findParent(node);
			if (parent == null) {
				if (node.left == null && node.right == null)
					root = null;
				else if (node.left == null) {
					root = node.right;
				} else if (node.right == null) {
					root = node.left;

				} else {
					// find leftmost node of right subtree. 
					Node rootCand = findLeftmost(root.right);
					rootCand.left = root.left;
					rootCand.size = root.size;
					rootCand.right = root.right;
				}
			} else {
				if (node.left == null && node.right == null) {
					if (parent.left == node)
						parent.left = null;
					else
						parent.right = null;
				} else if (node.left == null) {
					if (parent.left == node)
						parent.left = node.right;
					else
						parent.right = node.right;
				} else if (node.right == null) {
					if (parent.right == node)
						parent.right = node.left;
					else
						parent.left = node.left;
				} else {
					Node candidate = findLeftmost(node.right);
					candidate.left = node.left;
					candidate.size = node.size;
					candidate.right = node.right;
					if (parent.right == node)
						parent.right = candidate;
					else
						parent.left = candidate;
				}

			}
		}

		public Node findLeftmost(Node node) {
			if (node.left == null)
				return node;
			node.size--;
			return findLeftmost(node.left);
		}

		public Node findParent(Node node) {
			if (root == node)
				return null;
			Node cur = root;
			while (cur != node) {
				if (cur.score > node.score) {
					cur = cur.right;

				} else {
					cur.size--;
					cur = cur.left;
				}
			}
			return cur;
		}

		public List<String> findByRank(int k) {
			if (k < 0)
				return new ArrayList<String>();
			return findByRank(root, k);
		}

		public List<String> findByRank(Node node, int k) {
			if (node == null)
				return new ArrayList<String>();
			if (node.size > k)
				return findByRank(node.left, k);
			else if (node.size < k && node.size + node.IDSet.size() > k) {
				ArrayList<String> ans = new ArrayList<>();
				ans.addAll(node.IDSet);
				return ans;
			} else
				return findByRank(node.right, k - node.size - node.IDSet.size());

		}

	}

	@Test
	public void test() {
	}
}
