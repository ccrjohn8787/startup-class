public class Solution {
    /**
     * @param A an integer array
     * @param start an integer
     * @param end an integer
     * @return the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int[] prefix = new int[A.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }
        
        int result = 0;
        for (int i = 1; i < prefix.length; i++) {
            result += helper(prefix, prefix[i] - start + 1, 0, i) - helper(prefix, prefix[i] - end, 0, i);
        }
        
        return result;
    }
    
    public int helper(int[] A, int target, int left, int right) {
        if (A.length == 0 || left > right) {
            return 0;
        }
        
        int mid = 0;
        int result = 0;
        while (left <= right) {
            mid = left + (right - left)/2;
            if (A[mid] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
}
