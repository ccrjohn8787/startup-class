package Google;

import java.util.*;
import org.junit.Test;
/**
 * Created by siyuzhan on 5/25/16.
 */
public class WordAbbreviation {
    /**
     * 给一个string[]，给每个string一个不同的缩写，求缩写的character长度之和最小是多少
     */
    public int minSum(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            String abbr = getAbbr(str);
            if (!map.containsKey(abbr)) {
                map.put(abbr, new ArrayList<String>());
            }
            List<String> list = new ArrayList<>();
            list.add(str);
        }

        // sort by descending
        int count = 0;
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            List<String> list = entry.getValue();
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o2.length() - o1.length();
                }
            });

            // Use shortest abbr for longest word
            for (int i = 0; i < list.size(); i++) {
                count += Math.min(list.get(i).length(), entry.getKey().length() + i);
            }
        }
        return count;
    }

    public String getAbbr(String str) {
        if (str.length() <= 3) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0) + (str.length() - 2) + str.charAt(str.length() - 1));
        return sb.toString();
    }
}
