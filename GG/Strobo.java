/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('1','1');
        map.put('0','0');
        map.put('6','9');
        map.put('9','6');
        map.put('8','8');
        int left = 0, right = num.length() - 1;
        while(left <= right){
            // 如果字母不存在映射或映射不对，则返回假
            if(!map.containsKey(num.charAt(right)) || num.charAt(left) != map.get(num.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/
public class Solution {

     
    public List<String> findStrobogrammatic(int n) {
        List<String> result = new ArrayList<String>();
        Map<Character, Character> hashMap = new HashMap<>();
        fillHashMap(hashMap);
         
        char[] arr = new char[n];
        findStrobogrammaticHelper(arr, 0, n - 1, result, hashMap);
         
        return result;
    }
     
    private void findStrobogrammaticHelper(char[] arr, int lo, int hi, List<String> result, Map<Character, Character> hashMap) {
        if (lo > hi) {
            if (true) {
                result.add(new String(arr));
            }
            return;
        }
         
        for (Character c : hashMap.keySet()) {
            if (lo == 0 && c == '0' && arr.length > 1)
            {
                continue;
            }
            arr[lo] = c;
            arr[hi] = hashMap.get(c);
             
            if (lo < hi || (lo == hi && hashMap.get(c) == c)) {
                findStrobogrammaticHelper(arr, lo + 1, hi - 1, result, hashMap);
            }
        }
    }
     
    private void fillHashMap(Map<Character, Character> hashMap) {
        hashMap.put('0', '0');
        hashMap.put('1', '1');
        hashMap.put('8', '8');
        hashMap.put('6', '9');
        hashMap.put('9', '6');
    }
}


/*
III: A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

public class Solution {
    public int strobogrammaticInRange(String low, String high) {
        if (low == null || high == null || high.length() < low.length()) {
            return 0;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
        
        int cnt = 0;
        // for all different length of n
        for (int i = low.length(); i <= high.length(); i++) {
            int[] count = {0};
            char[] path = new char[i];
            helper(map, count, path, 0, i-1, low, high);
            cnt += count[0];
        }
        return cnt;
    }
    
    public void helper(HashMap<Character, Character> map, int[] count, char[] path, int lo, int hi, String lower, String upper) {
        if (lo > hi) {
            String curr = new String(path);
            if ((lower.length() < curr.length() || lower.compareTo(curr) <= 0) && 
                (curr.length() < upper.length() || curr.compareTo(upper) <= 0)) {
                count[0]++;
            }
            return;
        }
        for (Map.Entry<Character, Character> entry: map.entrySet()) {
            if (lo == 0 && entry.getKey() == '0' && path.length != 1) {
                continue;
            }
            // for '6' and '9'
            if (lo == hi && entry.getKey() != entry.getValue()) {
                continue;
            }
            // each time the lo and hi position would be overwritten and thus backtracked
            path[lo] = entry.getKey();
            path[hi] = entry.getValue();
            helper(map, count, path, lo+1, hi-1, lower, upper);
        }
    }
}
