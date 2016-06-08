package Airbnb;

/**
 * Created by siyuzhan on 5/15/16.
 */
import java.util.*;
import org.junit.Test;

public class StringConversion {
    public class StringConversionImpl {
        String[][] matrix;
        List<Character> ends;

        public StringConversionImpl(String[][] matrix, List<Character> ends) {
            this.matrix = matrix;
            this.ends = ends;
        }

        public boolean canConvert(String input) {
            HashMap<String, Boolean> map = new HashMap<>();
            return helper(input, map);
        }

        public boolean helper(String input, HashMap<String, Boolean> map) {
            if (map.containsKey(input)) {
                return map.get(input);
            }
            if (input.length() == 1 ) {
                if (ends.contains(input.charAt(0))) {
                    System.out.println(input);
                    return true;
                } else {
                    return false;
                }
            }

            for (int i = 0; i < input.length() - 1; i++) {
                String combined = matrix[input.charAt(i) - 'A'][input.charAt(i + 1) - 'A'];
                for (char c : combined.toCharArray()) {
                    if (helper(input.substring(0, i) + c + input.substring(i + 2, input.length()), map)) {
                        System.out.println(input);
                        return true;
                    }
                }
            }
            map.put(input, false);
            return false;
        }
    }

    @Test
    public void test(){
        String[][] matrix = {
                {"B", "AC", "D", "A"},
                {"D", "BC", "A", "D"},
                {"C", "B", "AD", "C"},
                {"AB", "B", "C", "D"}
        };
        List<Character> ends = new ArrayList<>();
        ends.add('C');
        ends.add('D');
        StringConversionImpl test = new StringConversionImpl(matrix, ends);
        System.out.println(test.canConvert("ABCD"));
    }
}
