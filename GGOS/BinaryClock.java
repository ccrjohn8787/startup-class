package Google; /**
 * Created by siyuzhan on 5/7/16.
 */
import java.util.*;

public class BinaryClock {
    public List<String> getAllTimes(int k) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i <= k; i++) {
            List<String> hour = new ArrayList<>();
            List<String> minutes = new ArrayList<>();
            helper(23, 4, i, 0, 0, hour);
            helper(59, 6, k-i, 0, 0, minutes);
            for (String hr: hour) {
                for (String mm: minutes) {
                    result.add(hr + ":" + mm);
                }
            }
        }
        return result;
    }

    public void helper(int max, int totalBits, int numOneBits, int currNum, int pos, List<String> result) {
        if (currNum > max) {
            return;
        }
        if (numOneBits == 0) {
            result.add((currNum < 10 ? "0" : "") + Integer.toString(currNum));
            return;
        }

        for (int i = pos; i <= totalBits; i++) {
            if ((currNum & (1 << i)) == 0) {
                currNum |= (1 << i);
                helper(max, totalBits, numOneBits - 1, currNum, i + 1, result);
                currNum &= (~(1 << i));
            }
        }
    }
}
