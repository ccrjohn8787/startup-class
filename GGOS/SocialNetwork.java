package Google; /**
 * Created by siyuzhan on 5/8/16.
 */
import java.util.*;

public class SocialNetwork {
    /**
     * 假设有个social network
     ( 1）返回朋友的朋友，但不是自己的朋友；
     ( 2 ) 返回朋友的朋友，担不是自己的朋友，按照出现的次数排序，也就是说加入一个人不是我的朋友，但是他是我所有的朋友的朋友，那么他应该是返回结果里面的第一个
     ONLY CONSIDER 2ND DEGREE FRIENDS
     */

    public List<String> getFriends(Node graph) {
        HashSet<String> directs = new HashSet<>();
        HashMap<String, Integer> mutual = new HashMap<>();
        return null;
    }

    public class Node {
        String name;
        List<Node> friends;
        public Node(String name) {
            this.name = name;
            this.friends = new ArrayList<>();
        }
    }
}
