package Google; /**
 * Created by siyuzhan on 5/7/16.
 */
import java.util.*;
public class OneOrZero {
    /**
     * A string consists of ‘0’, ‘1’ and '?'. The question mark can be either '0' or '1'. Find all possible combinations for a string.
     */

    List<String> getAllCombinations(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }
        helper(str, 0, "", result);
        return result;
    }

    public void helper(String str, int pos, String path, List<String> result) {
        if (pos == str.length()) {
            result.add(path);
            return;
        }
        if (str.charAt(pos) != '?') {
            helper(str, pos + 1, path + str.charAt(pos), result);
        } else {
            helper(str, pos + 1, path + '1', result);
            helper(str, pos + 1, path + '0', result);
        }
    }
}
