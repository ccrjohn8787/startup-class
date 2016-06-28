package Google;/**
 * Created by siyuzhan on 6/19/16.
 */

import java.util.*;

import org.junit.Test;

public class PerfectNumber {
    public List<Integer> getPerfectNumber(int num) {
        Cell[] dp = new Cell[num + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new Cell();
        }
        dp[0].count = 0;
        for (int i = 0; i <= num; i++) {
            for (int j = 1; j * j + i <= num; j++ ) {
                if (dp[i + j*j].count > dp[i].count + 1) {
                    dp[i + j*j].count = dp[i].count + 1;
                    dp[i + j*j].prev = j;
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(dp[i]);
        }
        List<Integer> result = new ArrayList<>();
        if (dp[num].count != Integer.MAX_VALUE) {
            result.add(0, dp[num].prev);
            int curr = num;
            while (dp[curr].count != 1) {
                int prev = dp[curr].prev;
                curr = curr - prev * prev;
                result.add(0, dp[curr].prev);
            }
        }
        return result;
    }

    public class Cell {
        int prev, count;
        public Cell() {
            this.prev = -1;
            this.count = Integer.MAX_VALUE;
        }
        public String toString() {
            return "prev: " + prev + " count: " + count;
        }
    }

    @Test
    public void test() {
        System.out.println(getPerfectNumber(34));
    }
}
