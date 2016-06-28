package Google;/**
 * Created by siyuzhan on 6/12/16.
 */

import java.util.*;

import org.junit.Test;

public class RemoveDuplicateCharacter {

    public void removeDup(char[] arr) {
        int i = 0; //next position for char
        int j = 0; // iterate
        while (j < arr.length) {
            while (j < arr.length - 1 && arr[j+1] == arr[j]) {
                j++;
            }
            arr[i] = arr[j];
            i++;
            j++;
        }
        while (i < arr.length) {
            arr[i] = ' ';
            i++;
        }
    }

    @Test
    public void test() {
        String str = "aabbbc";
        char[] arr = str.toCharArray();
        removeDup(arr);
        System.out.println(new String(arr));
    }
}
