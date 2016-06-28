package Google; /**
 * Created by siyuzhan on 5/7/16.
 */
import java.util.*;

public class FavoriteWords {
    /**
     * 给一个 array of words，和favorite letters， 让重新排序array，使得按照favorite letters的priority 排列。
     * 没有包含 favorite letters 的 words 则继续按照原本字母表排序
     举个栗子：array：['animal','duck','snake','zebra','horse','mouse'], favorite letter:'zh',
     output--->['zebra','horse','animal','duck','mouse','snake']
     */

    public void sort(String[] words, char[] favChars) {
        HashMap<Character, Integer> fav = new HashMap<>();
        for (int i = 0; i < favChars.length; i++) {
            fav.put(favChars[i], i);
        }

        Arrays.sort(words, new Comparator<String>(){
            public int compare(String s1, String s2) {
                for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
                    if (fav.containsKey(s1.charAt(i)) && !fav.containsKey(s2.charAt(i))) {
                        return -1;
                    } else if (fav.containsKey(s2.charAt(i)) && !fav.containsKey(s1.charAt(i))) {
                        return 1;
                    } else if (fav.containsKey(s1.charAt(i)) && fav.containsKey(s2.charAt(i))) {
                        if (fav.get(s1.charAt(i)) < fav.get(s2.charAt(i))) {
                            return -1;
                        } else if (fav.get(s1.charAt(i)) > fav.get(s2.charAt(i))) {
                            return 1;
                        }
                    }
                }
                return s1.compareTo(s2);
            }
        });

        for (String word: words) {
            System.out.println(word);
        }
    }
}
