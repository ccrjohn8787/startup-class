package Google;

import org.junit.Test;

/*
 * 函数签名为 int countChunk(String input)， 给定一个字符串，找出最多有多少个chunked palindrome,
 * 正常的palindrome是abccba, chunked palindrome的定义是：比如volvo, 可以把vo划分在一起，(vo) (l) (vo)，那么它是个palindrome。求实现返回最大的chunk 数量。
 */

public class ChunkedPalindrome {
	int countChunk(String input) {
		// runtime: O(n^3)
		// dp[i][j] = max num of chunks from input(i, j)
		// dp[i][j] = Math.max(dp[i+k][j-k] + 2) for k = 1 -> (j-i+1)/2 if
		// input(i, i+k) == input(j-k, j)

		if (input == null || input.length() == 0) {
			return 0;
		}
		int[][] dp = new int[input.length()][input.length()];
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
			if (i < dp.length - 1) {
				// if equal, then two separate chunks
				dp[i][i + 1] = (input.charAt(i) == input.charAt(i + 1) ? 2 : 1);
			}
		}

		for (int len = 3; len <= input.length(); len++) {
			for (int start = 0; start + len < input.length(); start++) {
				dp[start][start + len] = 1;
				for (int k = 1; k <= (len + 1) / 2; k++) {
					if (input.substring(start, start + k).equals(
							input.substring(start + len + 1 - k, start + len
									+ 1))) {
						dp[start][start + len] = Math.max(
								dp[start][start + len], dp[start + k][start
										+ len - k] + 2);
					}
				}
			}
		}
		return dp[0][input.length() - 1];
	}

	// Approach 2: O(n^2) greedy
	public int countChunks2(String str) {
		int count = 0;
		int i = 0, j = str.length() - 1; // scan from the two sides
		int prev_i = i, prev_j = j;
		while (i < j) {
			String chunk1 = str.substring(prev_i, i + 1);
			String chunk2 = str.substring(j, prev_j + 1);
			if (chunk1.equals(chunk2)) {
				count++;
				prev_i = i + 1;
				prev_j = j - 1;
			}
			i++;
			j--;
		}
		count *= 2;
		// if odd string
		if (i == j)
			count++;
		// even string and still has chunk left
		else if (prev_i < prev_j)
			count++;
		return count;
	}

	@Test
	public void test() {
		System.out.println(countChunk("abcdeabc"));
	}
}
