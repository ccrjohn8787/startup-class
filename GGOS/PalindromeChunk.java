package Google;

import org.junit.Test;

/**
 * Created by siyuzhan on 5/8/16.
 */
public class PalindromeChunk {

    int countChunk(String input) {
        // dp[i][j] = max num of chunks from input(i, j)
        // dp[i][j] = Math.max(dp[i+k][j-k] + 2) for k = 1 -> (j-i+1)/2 if input(i, i+k) == input(j-k, j)

        if (input == null || input.length() == 0) {
            return 0;
        }
        int[][] dp = new int[input.length()][input.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
            if (i < dp.length - 1) {
                dp[i][i + 1] = (input.charAt(i) == input.charAt(i + 1) ? 2 : 1);
            }
        }

        for (int len = 3; len <= input.length(); len++) {
            for (int start = 0; start + len < input.length(); start++) {
                dp[start][start+len] = 1;
                for (int k = 1; k <= (len + 1)/2; k++) {
                    if (input.substring(start, start+k).equals(input.substring(start+len+1-k, start+len+1))) {
                        dp[start][start+len] = Math.max(dp[start][start+len], dp[start+k][start+len-k] + 2);
                    }
                }
            }
        }
        return dp[0][input.length()-1];
    }

    @Test
    public void test() {
        System.out.println(countChunk("abcdeabc"));
    }
}
