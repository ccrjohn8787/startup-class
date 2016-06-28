package Google;/**
 * Created by siyuzhan on 6/19/16.
 */

import java.util.*;

import Util.Utilities;
import org.junit.Test;

public class FirstGreaterElementOnRight {

    public int[] getFirstGreaterElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>(); // decreasing stack


        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()] ) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }
        return result;
    }

    @Test
    public void test() {
        int[] arr = {2, 3, 1, 5};
        Utilities.print(getFirstGreaterElement(arr));
    }

}
