package Google;/**
 * Created by siyuzhan on 6/12/16.
 */

import java.util.*;

import org.junit.Test;

public class ReorderArray {
    /**
     * 一队人，每个人只知道自己前面有几个比自己高的，然后打乱顺序，告诉你每个人的高度，让你恢复原来的顺序
     */
    public int[] reorder(int[] arr) {
        int[] result = new int[arr.length];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            list.add(i);
        }
        return result;
    }
}
