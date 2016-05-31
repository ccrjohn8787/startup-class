package Uber;

/**
 * Created by siyuzhan on 5/20/16.
 */
import java.util.*;

public class RelativePositions{
    /**
     * (1)Input: 1 N 2, 2 NE 3, 3 S 1
     Expected output: False
     (2)Input :  4 SE 5, 5 SW 3, 3 N 4
     Expected output: True
     */
    public boolean isValid(String[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return true;
        }

        HashMap<String, Node> map = new HashMap<>();
        for (String[] pair: pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], new Node(pair[0]));
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], new Node(pair[1]));
            }

            for (char c: pair[2].toCharArray()) {
                if (c == 'N') {
                    map.get(pair[0]).neighborsN.add(map.get(pair[1]));
                } else if (c == 'S') {
                    map.get(pair[1]).neighborsN.add(map.get(pair[0]));
                } else if (c == 'E') {
                    map.get(pair[0]).neighborsE.add(map.get(pair[1]));
                } else if (c == 'W') {
                    map.get(pair[1]).neighborsE.add(map.get(pair[0]));
                }
            }
        }

        // check cycle in both directions
        HashSet<String> visited = new HashSet<>();
        HashSet<String> tempVisited = new HashSet<>();
        for (Node node: map.values()) {
            if (hasCycle(node, 0, visited, tempVisited) || hasCycle(node, 1, visited, tempVisited)) {
                return false;
            }
        }
        return true;
    }

    public class Node {
        String id;
        Set<Node> neighborsN;
        Set<Node> neighborsE;
        int indegree;
        public Node(String id) {
            this.id = id;
            this.neighborsN = new HashSet<>();
            this.neighborsN = new HashSet<>();
            this.indegree = 0;
        }

        public int hashcode() {
            return id.hashCode();
        }
        public boolean equals(Object other) {
            return (other instanceof Node) && ((Node)(other)).id == id;
        }
    }

    public boolean hasCycle(Node node, int direction, HashSet<String> visited, HashSet<String> tempVisited) {
        if (visited.contains(node.id)) {
            return false;
        }
        if (tempVisited.contains(node.id)) {
            return true;
        }
        tempVisited.add(node.id);
        Set<Node> neighbors = null;
        if (direction == 0) {
            neighbors = node.neighborsN;
        } else {
            neighbors = node.neighborsE;
        }
        for (Node n: neighbors) {
            if (hasCycle(n, direction, visited, tempVisited)) {
                return true;
            }
        }
        tempVisited.remove(node.id);
        visited.add(node.id);
        return false;
    }
}
