package Google;
import java.util.*;
import org.junit.Test;
import org.junit.Assert;

/*
 * top 4 LEDs  is binary for hours
bottom 6 LEDs for minutes
_ _ _ _ = hours
_ _ _ _ _ _ = minutes
0001
000011
1:03
3 LEDs to be lit up?
1) find total number of combinations for 3 LEDs lit up
2) follow up: return all the actual combinations of hours: minutes
 */

public class LEDClockCombinations {
	
	/*
	 * !!!Combination of 3, sample code
	 */
	public int combinations() {
		int[] hours = counter(12);
		int[] minutes = counter(60);
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result += hours[i] * minutes[3 - i];
		}
		return result;
	}

	private int[] counter(int max) {
		// counter[i] is the total count of numbers below max that has i number of 1's
		int[] counter = new int[4];
		for (int i = 0; i <= max; i++) {
			int cur = i;
			int count = 0;
			while (cur > 0) {
				if ((cur & 1) == 1)
					count++;
				cur >>= 1;
				if (count == 4)
					break;
			}
			if (count < 4)
				counter[count]++;
		}
		return counter;
	}
	
	// Max value of Hour is 12, 1100
	// Max value of Minute is 59, 111011
	public int totalNumberOfCombinations(int n)
	{
		int result = 0;
		for (int i = 0; i <= n; i++)
		{
			if (i >= 4 || n-i >= 6)
			{
				continue;
			}
			result += getCombinations(i, 4, 11) * getCombinations(n-i, 6, 59);
		}
		return result;
	}
	
	private int getCombinations(int numOneBits, int totalBits, int max)
	{
		// get combinations with a max
		List<Integer> results = new ArrayList<Integer>();
		int num = 0;
		helper(numOneBits, totalBits, max, num, 0, results);
		//Collections.sort(results);
		//System.out.println("numOneBits: " + numOneBits + " totalBits: " + totalBits + "max: " + max + "results: " + results);
		return results.size();
	}
	
	private void helper(int remainingBits, int totalBits, int max, int curNum, int pos, List<Integer> results)
	{
		if (pos == totalBits)
		{
			// pitfall: only add to result if used all bits
			if (remainingBits == 0)
			{
				results.add(curNum);
			}
			return;
		}
		
		// set ith bit
		// Pitfall: max can not be reached: (e.g. not allow 11:60 or 12:00)
		if (remainingBits > 0 && (curNum | (1 << pos)) <= max)
		{
			helper(remainingBits - 1, totalBits, max, (curNum | (1 << pos)), pos+1, results);
		}
		// not set ith bit
		helper(remainingBits, totalBits, max, curNum, pos+1, results);
	}

	// Follow up problem solution
	public List<String> getAllClockCombinations()
	{
		List<Integer> hours = new ArrayList<Integer>();
		getCombinations(4, 11, 0, 0, hours);
		List<Integer> minutes = new ArrayList<Integer>();
		getCombinations(6, 59, 0, 0, minutes);
		
		// merge all hours and minutes and convert to string
		List<String> result = new ArrayList<String>();
		for (int hour: hours)
		{
			for (int minute: minutes)
			{
				result.add(getString(hour, 4) + ":" + getString(minute, 6));
			}
		}
		return result;
	}
	
	private String getString(int num, int digits)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digits; i++)
		{
			sb.insert(0, ((num & (1 << i)) >> i));
		}
		return sb.toString();
	}
	
	public void getCombinations(int totalBits, int max, int curNum, int pos, List<Integer> result)
	{
		if (pos == totalBits)
		{
			result.add(curNum);
			return;
		}
		
		// set ith bit
		// Pitfall: max can not be reached: (e.g. not allow 11:60 or 12:00)
		if ((curNum | (1 << pos)) <= max)
		{
			getCombinations(totalBits, max, (curNum | (1 << pos)), pos+1, result);
		}
		// not set ith bit
		getCombinations(totalBits, max, curNum, pos+1, result);
	}
	@Test
	public void test() {
		System.out.println(totalNumberOfCombinations(3));
		// follow up
		System.out.println(getAllClockCombinations());
	}
}
