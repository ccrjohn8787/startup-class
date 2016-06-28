package Google;/**
 * Created by siyuzhan on 6/8/16.
 */

import java.util.*;

import org.junit.Test;

public class WeightedEquilibriumIndex {
    public int findIdx(int[] arr) {
        int rsum = 0;
        for (int i = 0; i < arr.length; i++) {
            rsum += arr[i];
        }
        int lsum = 0;
        for (int i = 0; i < arr.length; i++) {
            lsum += arr[i];
            rsum -= arr[i];
            if (lsum == rsum) {
                return i;
            }
        }
        return 0;
    }

    public int findWeightedIdx(int[] arr) {
        int[] prefixSum = new int[arr.length + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i-1] + arr[i-1];
        }
        int rsum = 0;
        for (int i = 0; i < arr.length; i++) {
            rsum += arr[i] * i;
        }
        int lsum = 0;

        for (int i = 1; i < arr.length; i++) {
            // index is tricky here!
            lsum += prefixSum[i] - prefixSum[0];
            rsum -= prefixSum[prefixSum.length - 1] - prefixSum[i];
            if (lsum == rsum) {
                return i;
            }
        }
        return 0;
    }

    @Test
    public void test() {
        int[] arr = {1, 3, 11, 3, 1};
        System.out.println(findIdx(arr));
        System.out.println(findWeightedIdx(arr));
    }
}
