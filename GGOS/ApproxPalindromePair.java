package Google; /**
 * Created by siyuzhan on 5/7/16.
 */
import java.util.*;

public class ApproxPalindromePair {
    /**
     * 找这样的pair <A, B>，有两个条件, (1) A单词的后两个字母和B单词的前两个字母一样 （2）A单词的第一个字母和B单词的最后一个字母一样
     * Find the longest length of such pair
     */

    public int longestPair(Set<String> dict) {
        HashMap<String, ArrayList<String>> map = new HashMap<>(); // key is first-2-letters + last-letter, value is word length
        for (String word: dict) {
            String abbr = (word.length() < 3) ? word : word.substring(0, 2) + word.charAt(word.length()-1);
            ArrayList<String> list = null;
            if (map.containsKey(abbr)) {
                list = map.get(abbr);
            } else {
                list = new ArrayList<>();
            }
            list.add(word);
            map.put(abbr, list);
        }

        int maxLength = 0;
        for (String word: dict) {
            String abbr = (word.length() < 3) ? reverse(word) : word.substring(word.length() - 2, word.length()) + word.charAt(0);
            if (map.containsKey(abbr)) {
                ArrayList<String> list = map.get(abbr);
                for (String w: list) {
                    if (word.equals(w)) {
                        continue;
                    }
                    maxLength = Math.max(maxLength, word.length() + w.length());
                }
            }
        }
        return maxLength;
    }

    public String reverse(String word) {
        char[] arr = word.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }
}
