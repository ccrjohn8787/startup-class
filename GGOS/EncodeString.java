package Google;/**
 * Created by siyuzhan on 6/8/16.
 */

import java.util.*;

import org.junit.Test;

public class EncodeString {
    public String encode(String input) {
        // general rules:
        // 1. if see more than 3 same chars, encode to num x char
        // 2. if see num x (char/num) -> change to 1 x num x 1 x (char/num)

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int count = 1;
        while (i < input.length()) {
            if (Character.isDigit(input.charAt(i))) {
                if (i < input.length() - 2 && input.charAt(i + 1) == 'x') {
                    sb.append("1x" + input.charAt(i) + 'x' + "1x" + input.charAt(i+2));
                    i+=2;
                } else {
                    sb.append(input.charAt(i));
                }
            } else {
                while (i < input.length() - 1 && input.charAt(i + 1) == input.charAt(i)) {
                    count++;
                    i++;
                }
                if (count == 1) {
                    sb.append(input.charAt(i));
                } else {
                    sb.append(count + "x" + input.charAt(i));
                }
                count = 1;
            }
            i++;
        }
        return sb.toString();
    }

    public String decode(String input) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            if (Character.isDigit(input.charAt(i))) {
                if (i < input.length() - 2 && input.charAt(i + 1) == 'x') {
                    for (int j = 0; j < input.charAt(i) - '0'; j++) {
                        sb.append(input.charAt(i+2));
                    }
                    i+=2;
                } else {
                    sb.append(input.charAt(i));
                }
            } else {
                sb.append(input.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(encode("aabbbc"));
        System.out.println(encode("12x2abbbc"));
        System.out.println(encode("12345x"));
        System.out.println(decode(encode("12x2abbbc")));
    }
}
