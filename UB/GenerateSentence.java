package Uber;

/**
 * Created by siyuzhan on 5/20/16.
 */
import java.util.*;
import org.junit.Test;

public class GenerateSentence {
    // input: sentences
// e.g.
// a fox jumps over a rock.
// a frog jumps over a pond..
//.....
// output: sentence.

    public String generateSentences(List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }

        HashMap<String, Node> map = new HashMap<>();

        for (String str: list) {
            String[] words = str.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (!map.containsKey(words[i])) {
                    map.put(words[i], new Node(words[i]));
                }
            }
            for (int i = 0; i < words.length - 1; i++) {
                String curr = words[i];
                String next = words[i+1];
                map.get(curr).neighbors.add(map.get(next));
                map.get(next).degree++;
            }
        }

        // pick a starting word and dfs
        Stack<Node> stack = new Stack<>();
        stack.push(map.get("a"));

        List<String> words = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        visited.add(stack.peek().word);

        Random rand = new Random();

        while (!stack.isEmpty()) {
            // ending condition could be if found a word with period
            Node curr = stack.pop();
            words.add(curr.word);
            // could randomly pick a neighbor here
            if (curr.neighbors.size() > 1) {
                int ran = rand.nextInt(curr.neighbors.size() - 1);
                stack.push(curr.neighbors.get(ran));
            } else if (curr.neighbors.size() == 1) {
                stack.push(curr.neighbors.get(0));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            if (i < words.size() - 1) {
                sb.append(" ");
            }
        }
        sb.append(".");
        return sb.toString();
    }

    class Node {
        String word;
        int degree;
        List<Node> neighbors;
        public Node(String word) {
            this.word = word;
            this.degree = 0;
            this.neighbors = new ArrayList<>();
        }
    }

    //a fox jumps over a rock.
// a frog jumps over a pond.
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("a fox jumps over a rock");
        list.add("a frog jumps over a pond");
        System.out.println(generateSentences(list));
    }
}
