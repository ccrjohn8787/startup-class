package Snapchat;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by siyuzhan on 4/30/16.
 */
// TODO: Implement Big Float add, subtract, multiply
public class BigFloat {
    public String add(String num1, String num2) {
        if (num1.charAt(0) != '-' && num2.charAt(0) == '-') {
            return subtract(num1, num2.substring(1));
        }
        if (num1.charAt(0) == '-' && num2.charAt(0) != '-') {
            return subtract(num2, num1.substring(1));
        }
        boolean appendMinus = false;
        if (num1.charAt(0) == '-' && num2.charAt(0) == '-') {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
            appendMinus = true;
        }
        num1 = reverse(num1);
        num2 = reverse(num2);
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(num1.length(), num2.length()); i++) {
            int digit1 = i < num1.length() ? (int)(num1.charAt(i) - '0') : 0;
            int digit2 = i < num2.length() ? (int)(num2.charAt(i) - '0') : 0;
            int currSum = digit1 + digit2 + carry;
            sb.append(currSum%10);
            carry = currSum/10;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        if (appendMinus) {
            sb.append('-');
        }
        return reverse(sb.toString());
    }

    public String subtract(String num1, String num2) {
        if (num1.charAt(0) == '-' && num2.charAt(0) != '-') {
        	// negative number subtract positive number
            return "-" + add(num1.substring(1), num2);
        }
        if (num1.charAt(0) != '-' && num2.charAt(0) == '-') {
        	// positive number subtract negative number
            return add(num1, num2.substring(1));
        }
        boolean appendMinus = false;
        if (num1.charAt(0) == '-' && num2.charAt(0) == '-' ) {
            // both negative
            if (compare(num1.substring(1), num2.substring(1)) < 0){
            	// e.g. -2 - (-20)
                String temp = num1.substring(1);
                num1 = num2.substring(1);
                num2 = temp;
            } else if (compare(num1.substring(1), num2.substring(1)) > 0) {
            	// e.g. -20 - (-2)
                num1 = num1.substring(1);
                num2 = num2.substring(1);
                appendMinus = true;
            } else {
                return "0";
            }
        } else {
            // both positive
            if (compare(num1, num2) < 0) {
                String temp = num1;
                num1 = num2;
                num2 = temp;
                appendMinus = true;
            } else if (compare(num1, num2) == 0) {
                return "0";
            }
        }

        // Now we have num1 and num2 both positive, and num1 > num2
        num1 = reverse(num1);
        num2 = reverse(num2);
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(num1.length(), num2.length()); i++) {
            int digit1 = i < num1.length() ? (int)(num1.charAt(i) - '0') : 0;
            int digit2 = i < num2.length() ? (int)(num2.charAt(i) - '0') : 0;
            int curr = digit1 - digit2 - carry;
            if (curr < 0) {
                curr += 10;
                carry = 1;
            } else {
                carry = 0;
            }
            sb.append(curr);
        }
        // remove the zeros 
        for (int i = sb.length()-1; i >= 0; i--)
        {
        	if (sb.charAt(i) != '0')
        	{
        		break;
        	}
        	sb.deleteCharAt(i);
        }
        if (appendMinus) {
            sb.append('-');
        }
        return reverse(sb.toString());
    }


    public String multiply(String num1, String num2) {
        boolean appendMinus = false;
        if (num1.charAt(0) == '-' && num2.charAt(0) == '-') {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
        } else if (num2.charAt(0) == '-') {
            num2 = num2.substring(1);
            appendMinus = true;
        } else if (num1.charAt(0) == '-') {
            num1 = num1.substring(1);
            appendMinus = true;
        }

        num1 = reverse(num1);
        num2 = reverse(num2);
        int carry = 0;
        int[] arr = new int[num1.length() + num2.length()];

        for (int i = 0; i < num1.length(); i++) {
            int val = num1.charAt(i) - '0';
            for (int j = 0; j < num2.length(); j++) {
            	// arr[i+j] is the designated position of num1[i] * num2[j]
                int curr = arr[i + j] + carry + (num2.charAt(j) - '0') * val;
                arr[i + j] = curr % 10;
                carry = curr / 10;
            }
            if (carry != 0) {
                arr[i + num2.length()] = carry;
                carry = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (appendMinus) {
            sb.append("-");
        }
        int i = arr.length - 1;
        while (i > 0 && arr[i] == 0) {
            i--;
        }
        if (i == 0) {
            sb.append(arr[i]);
            return sb.toString();
        }

        for (; i >= 0; i--) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }
    
    // TODO: Implement divide
    public String divide(String num1, String num2) {
        return "0";
    }

    private String reverse(String str) {
        int i = 0;
        int j = str.length() - 1;
        char[] arr = str.toCharArray();
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }

    private int compare(String num1, String num2) {
        if (num1.length() != num2.length()) {
            return num1.length() - num2.length();
        }
        // can just return num1.compareTo(num2)
        return num1.compareTo(num2);
        /*
        int i = 0;
        while (i < num1.length() && num1.charAt(i) == num2.charAt(i)) {
            i++;
        }
        if (i == num1.length()) {
            return 0;
        }
        return num1.charAt(i) - num2.charAt(i);
        */
    }
    
    @Test
    public void test()
    {
    	Assert.assertEquals(subtract("100", "9"), "91");
    	Assert.assertEquals(subtract("-1000", "-9"), "-991");
    	Assert.assertEquals(multiply("-20", "4"), "-80");
    }

}
