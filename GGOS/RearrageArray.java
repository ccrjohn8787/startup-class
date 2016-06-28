package Google;/**
 * Created by siyuzhan on 6/12/16.
 */

import java.util.*;

import Util.Utilities;
import org.junit.Test;

public class RearrageArray {
    /**
     * 设计一个class来re-arrange array。一开始会给两个array，这两个array存在一个pattern，然后给你一个input array，让你根据pattern给output，
     * 例如：A:1,2,3,4 -->B: 4,3,2,1这样的pattern， 如果在给你一组数是5678，那你就返回8765。相当于对应index
     * @param a
     * @param b
     * @param input
     * @return
     */
    public int[] rearrange(int[] a, int[] b, int[] input) {
        if (a == null || b == null || input == null || a.length != b.length || a.length != input.length) {
            return null;
        }

        // find pattern
        HashMap<Integer, List<Integer>> aMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (!aMap.containsKey(a[i])) {
                aMap.put(a[i], new ArrayList<>());
            }
            List<Integer> list = aMap.get(a[i]);
            list.add(i);
        }
        HashMap<Integer, Integer> pattern = new HashMap<>();
        for (int i = 0; i < b.length; i++) {
            if (aMap.containsKey(b[i])) {
                pattern.put(i, aMap.get(b[i]).get(0));
                List<Integer> list = aMap.get(b[i]);
                list.remove(0);
            }
        }

        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[pattern.get(i)] = input[i];
        }
        return result;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4};
        int[] b = {4, 3, 2, 1};
        int[] input = {5, 6, 7, 8};
        Utilities.print(rearrange(a, b, input));
    }
}
