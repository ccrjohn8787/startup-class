package Google; /**
 * Created by siyuzhan on 5/7/16.
 */
import org.junit.*;
import org.junit.Test;

import java.util.*;

public class MostFrequentNumber {
    /**
     * 给一个sorted int array 定义popular item的frequency/occurerence 大于N/4
     求item 值最小的frequency.
     */

    public int getMostFrequent(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length < 4) {
            return arr[0];
        }
        int len = arr.length/4;
        List<Integer> candidates = new ArrayList<>();

        int i = 0;
        while (i < arr.length) {
            candidates.add(arr[i]);
            i+=len;
        }

        for (Integer candidate: candidates) {
            int range = getRange(arr, candidate);
            if (range > arr.length/4.0) {
                return candidate;
            }
        }
        return 0;
    }

    public int getRange(int[] arr, int target) {
        // find lower bound
        int start = 0;
        int end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (target <= arr[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        int lowerBound = (arr[start] == target) ? start : end;

        // find upper bound
        start = 0;
        end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (target < arr[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        int upperBound = (arr[end] == target) ? end : start;
        return upperBound - lowerBound + 1;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 2, 3, 5};
        System.out.println(getMostFrequent(arr));
    }
}
