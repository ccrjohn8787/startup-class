package Google; /**
 * Created by siyuzhan on 5/8/16.
 */
import java.util.*;

public class SubListEqualSet {
    /**
     * 已知一个List里全是interger, 还有一个set里也全是integer,求是否存在一个sublist, set里所有元素和sublist中所有元素一一对应。
     * [sublist 这里要求必须为原List的连续项， 注意set里元素无序]
     * http://www.1point3acres.com/bbs/forum.php?mod=redirect&goto=findpost&ptid=160196&pid=2192057&fromuid=206597
     */

    public boolean isSublistEqualsSet(List<Integer> list, Set<Integer> set) {

        if (set == null || list == null) {
            return false;
        }
        if (set.size() == 0) {
            return true;
        }
        if (list.size() == 0) {
            return false;
        }
        int start = 0;
        int pos = 0;
        System.out.println(set.size());
        Set<Integer> tmp = new HashSet<Integer>();
        while (pos < list.size()) {
            int cur = list.get(pos);
            if (set.contains(cur)) {
                while (start < pos && tmp.contains(cur)) {
                    tmp.remove(list.get(start));
                    start++;
                }
                tmp.add(cur);
                if (tmp.size() == set.size()) {
                    return true;
                }
            } else {
                start = pos + 1;
                tmp.clear();
            }
            pos++;
        }

        return false;
    }

}
