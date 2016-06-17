package Google;
import java.util.*;
import org.junit.Test;
import org.junit.Assert;

/*
 * 给一个sorted int array 定义popular item的frequency/occurerence 大于N/4. 求item 值最小的frequency.
 */
public class PopularItem {
	public int getPopularItemFrequency(int[] sortedArr)
	{
		// get the frequency of the smallest popular item
		// Idea: popular item has to be in one of those: (all numbers in the array are indices): [0, k, 2k, ...]
		// where k = Ceiling(sortedArr.length/4)
		int increment = (int)Math.ceil(sortedArr.length/4);
		for (int i = 0; i < sortedArr.length; i += increment)
		{
			int frequency = getFrequency(sortedArr, sortedArr[i]);
			if (frequency > sortedArr.length/4)
			{
				return frequency;
			}
		}
		// no popular item
		return 0;
	}
	
	private int getFrequency(int[] arr, int item)
	{
		int firstOccurIdx = getFirstIdx(arr, item);
		int lastOccurIdx = getLastIdx(arr, item);
		return lastOccurIdx - firstOccurIdx + 1;
	}
	
	private int getFirstIdx(int[] arr, int item)
	{
		int start = 0, end = arr.length-1;
		while (start + 1 < end)
		{
			int mid = start + (end - start)/2;
			if (arr[mid] >= item)
			{
				end = mid;
			}
			else
			{
				start = mid;
			}
		}
		if (arr[start] == item)
		{
			return start;
		}
		else
		{
			return end;
		}
	}
	
	private int getLastIdx(int[] arr, int item)
	{
		int start = 0, end = arr.length-1;
		while (start + 1 < end)
		{
			int mid = start + (end - start)/2;
			if (arr[mid] > item)
			{
				end = mid;
			}
			else
			{
				start = mid;
			}
		}
		if (arr[end] == item)
		{
			return end;
		}
		else
		{
			return start;
		}
	}
	
	@Test
	public void test() {
		int[] arr = {0,1,2,3,3,3,4,5,5};
		Assert.assertEquals(getPopularItemFrequency(arr), 3);
		arr = new int[]{0,1,2};
		Assert.assertEquals(getPopularItemFrequency(arr), 1);
		arr = new int[]{1,2,2,3};
		Assert.assertEquals(getPopularItemFrequency(arr), 2);
		arr = new int[]{0,1,2,3,4,5,6,7};
		Assert.assertEquals(getPopularItemFrequency(arr), 0);
	}
}
