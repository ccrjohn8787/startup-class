package Google;/**
 * Created by siyuzhan on 6/19/16.
 */

import java.util.*;

import org.junit.Test;

public class TextJustification {
    public int justify(List<String> words, int m, int n) {
        int size = words.size();
        int count = 0;

        int currRow = 0;
        while (currRow < m) {
            int currPos = 0;
            while (currPos <= n) {
                currPos += words.get(count%size).length() + 1;
                count++;
            }
            if (currPos > n + 1) {
                currPos -= words.get(count % size).length() - 1;
                count--;
            }
            currRow++;
        }
        return count/size;
    }
}
