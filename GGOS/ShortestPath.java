package Google; /**
 * Created by siyuzhan on 5/7/16.
 */
import java.util.*;
import org.junit.Test;

public class ShortestPath {
    /**
     * 给一个undirected graph，举个栗子如下：
     0-(0)--1--(1)--2
     \        \
     (1)    (1)
     \        \
     3-(0)--4. from
     给两个数字，代表起点和终点，请问最短的从起点到终点的路径，且最多经过一个（0）的arc
     */

    public List<String> getShortestPath(List<Node> graph, int start, int end) {
        Node startNode = null;
        for (Node node: graph) {
            if (node.val == start) {
                startNode = node;
                break;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(startNode);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(startNode.val);

        List<String> path = new ArrayList<>();
        Node endNode = null;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.val == end) {
                endNode = curr;
                break;
            }
            for (Node node: curr.neighbors.keySet()) {
                if (node.prev == null ||
                        (curr.hasSeenZero == false && node.hasSeenZero && curr.neighbors.get(node) == 1)) {
                    node.prev = curr;
                    if (node.prev == null) {
                        node.hasSeenZero = (curr.hasSeenZero || curr.neighbors.get(node) == 0);
                    } else {
                        node.hasSeenZero = curr.hasSeenZero;
                    }
                    queue.offer(curr);
                }

            }
        }

        if (endNode == null) {
            return path;
        }

        while (endNode.val != start) {
            path.add(0, Integer.toString(endNode.val));
            endNode = endNode.prev;
        }
        path.add(0, Integer.toString(start));
        return path;
    }

    public class Node {
        int val;
        boolean hasSeenZero;
        HashMap<Node, Integer> neighbors;
        Node prev;
        public Node(int val) {
            this.val = val;
            this.hasSeenZero = false;
            this.neighbors = new HashMap<>();
            this.prev = null;
        }
    }

    @Test
    public void test() {
        Node a = new Node(1);
        Node b = new Node(1);
        Node c = new Node(1);
        Node d = new Node(1);
        Node e = new Node(1);
        Node f = new Node(1);

    }
}