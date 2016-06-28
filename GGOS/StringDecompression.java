package Google; /**
 * Created by siyuzhan on 5/7/16.
 */
import java.util.*;

public class StringDecompression {
    /**
     * 2[abc]3[a]c => abcabcaaac;     2[ab3[d]]2[cc] => abdddabdddcc
     输入                   输出
     */

    public String decompress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        Stack<Character> charStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(str.charAt(i))) {
                    sb.append(str.charAt(i));
                    i++;
                }
                numStack.push(Integer.parseInt(sb.toString()));
                i--; //!!! important!!! for-loop will increment one more time
            } else {
                if (str.charAt(i) == ')') {
                    int numRep = (numStack.isEmpty() ? 1 : numStack.pop());
                    StringBuilder sb = new StringBuilder();
                    while (!charStack.isEmpty() && charStack.peek() != '(') {
                        sb.insert(0, charStack.pop());
                    }
                    if (!charStack.isEmpty() && charStack.peek() == '(') {
                        charStack.pop();
                    }
                    for (int j = 0; j < numRep; j++) {
                        for (char c : sb.toString().toCharArray()) {
                            charStack.push(c);
                        }
                    }
                } else {
                    charStack.push(str.charAt(i));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!charStack.isEmpty()) {
            sb.insert(0, charStack.pop());
        }
        return sb.toString();
    }
}
