package Google;/**
 * Created by siyuzhan on 6/12/16.
 */

import java.util.*;

import org.junit.Test;

public class RearrangeChars {
    public String rearrange(String str, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: str.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c ,1);
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(10, new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return b.val - a.val;
            }
        });

        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            queue.offer(new Node(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            int count = 0;
            List<Node> toAdd = new ArrayList<>();
            while (count < k && !queue.isEmpty()) {
                Node curr = queue.poll();
                sb.append(curr.c);
                curr.val--;
                if (curr.val != 0) {
                    toAdd.add(curr);
                }
                count++;
                i++;
            }
            if (count != k) {
                return null;
            }
            for (Node n: toAdd) {
                queue.offer(n);
            }
        }
        return sb.toString();
    }

    public class Node {
        char c;
        int val;
        public Node (char c, int val) {
            this.c = c;
            this.val = val;
        }
    }

    @Test
    public void test() {
        System.out.println(rearrange("aabbbc", 2));
    }
}
