package Google;/**
 * Created by siyuzhan on 6/18/16.
 */

import java.util.*;

import org.junit.Test;

public class GraphPattern {
    public List<Node> getNodes(Node graph) {
        //reverse graph first
        List<Node> result = new ArrayList<>();
        HashSet<Node> visited = new HashSet<>();
        dfs(graph, result, visited);
        return result;
    }

    public void dfs(Node curr, List<Node> result, HashSet<Node> visited) {
        if (curr.color.equals("red")) {
            dfsRed(curr, 0, result, visited);
        }
        for (Node n: curr.neighbors) {
            dfs(n, result, visited);
        }
    }

    public void dfsRed(Node curr, int greenCount, List<Node> result, HashSet<Node> visited) {
        if (greenCount >= 1) {
            result.add(curr);
            visited.add(curr);
        }

        if (!curr.color.equals("green")) {
            return;
        }

        for (Node n: curr.neighbors) {
            if (n.color.equals("green") && !visited.contains(n)) {
                greenCount++;
                dfsRed(n, greenCount, result, visited);
                greenCount--;
            } else if (greenCount >= 1 && visited.contains(n)) {
                dfsRed(n, greenCount, result, visited);
            }
        }
    }

    public class Node {
        String color;
        List<Node> neighbors;
        public Node(String color) {
            this.color = color;
            this.neighbors = new ArrayList<>();
        }
    }
}
