package Google; /**
 * Created by siyuzhan on 5/7/16.
 */
import java.util.*;
import org.junit.Test;

public class SumOfSubtrees {
    /**
     * 给你一组Treenode，他们每个有一个id，一个parentId，一个value，让你输出所有subtree的sum of value
     */

    public class TreeNode {
        int id, parentId, val;
        public TreeNode(int id, int parentId, int val) {
            this.id = id;
            this.parentId = parentId;
            this.val = val;
        }
    }

    public HashMap<Integer, Integer> getSum(List<TreeNode> nodes) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        for (TreeNode node: nodes) {
            getSumHelper(node, nodes, sumMap);
        }
        return sumMap;
    }

    public int getSumHelper(TreeNode node, List<TreeNode> nodes, HashMap<Integer, Integer> map) {
        if (map.containsKey(node.id)) {
            return map.get(node.id);
        }
        int sum = node.val;
        for (TreeNode n: nodes) {
            if (n.parentId == node.id) {
                sum += getSumHelper(n, nodes, map);
            }
        }
        map.put(node.id, sum);
        return sum;
    }
    @Test
    public void test() {
        List<TreeNode> list = new ArrayList<>();
        TreeNode node1 = new TreeNode(1, -1, 1);
        TreeNode node2 = new TreeNode(2, 1, 2);
        TreeNode node3 = new TreeNode(3, 2, 3);
        TreeNode node4 = new TreeNode(4, 2, 4);
        TreeNode node5 = new TreeNode(5, 2, 5);
        TreeNode node6 = new TreeNode(6, 1, 6);
        TreeNode node7 = new TreeNode(7, 1, 7);
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);
        System.out.println(getSum(list));
    }
}
