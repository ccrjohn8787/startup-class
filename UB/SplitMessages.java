package Uber;

import java.util.*;
import org.junit.Test;
/*
 * - 题目：给一个长消息，分拆成几个短消息，每个短消息有一个长度限制。一个单词不能分拆到两个短消息中。然后在每个短消息后面加上页码信息，如 (1/3),(2/3),(3/3)，
 * 这个新加入的页码信息也计算入每个短消息的长度限制.
经过询问之后得到更多详细要求及假设：（1）消息数量尽可能少，不必凑整句，可以在任意空格处分页；（2）这个固定长度可能非法，比如某个词很长导致一条消息放不下，要判断并抛出异常；
（3）假设空格分割，不会出现连着两个空格的情况。
这个题目看着挺简单，但真正trick的地方是页码（长度：1位，2位还是3位）数量只有在切分短消息之后才知道，而切分短消息又必须基于页码长度。

 所以这个题的解决方案其实是backtracking。假设短消息少于10条，页码信息就是5位,比如(1/3).然后切分消息，如果消息总数多余10条，那么重新来过，假设消息少于100条...
 依次，直到切分的消息总数跟页码长度匹配为止。
 */
public class SplitMessages {
    public List<String> splitMessage(String str, int len) {
        List<String> result = new ArrayList<>();
        // total number of pages (short messages)
        int totalPageLen = 1;

        List<List<String>> tmpResult = new ArrayList<>();
        boolean isValid = helper(str, len, totalPageLen, tmpResult);
        // backtrack to try next 
        while (!isValid) {
            tmpResult.clear();
            totalPageLen++;
            isValid = helper(str, len, totalPageLen, tmpResult);
        }

        int totalPages = tmpResult.size();
        for (int j = 0; j < tmpResult.size(); j++) {
            List<String> page = tmpResult.get(j);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < page.size(); i++) {
                sb.append(page.get(i));
                if (i != page.size() - 1) {
                    sb.append(" ");
                }
            }
            sb.append("(" + (j+1) + "/" + totalPages + ")");
            result.add(sb.toString());
        }
        return result;
    }

    public boolean helper(String str, int len, int totalPageLen, List<List<String>> result) {
        String[] words = str.split(" ");
        int wordLen = words.length;
        int pos = 0;
        int currPage = 1; // starting from 1
        while (pos < wordLen) {
            ReturnType rType = helper(words, pos, len, currPage, totalPageLen);
            pos = rType.nextPos;
            result.add(rType.words);
            currPage++;
            if (result.size() >= Math.pow(10, totalPageLen)) {
                return false;
            }
        }
        return true;
    }

    public ReturnType helper(String[] words, int pos, int len, int currPage, int totalPageLen) {
        List<String> page = new ArrayList<>();
        int currLength = 0;
        int maxWidth = len - ((int)(Math.log(currPage)/Math.log(10)) + 1) - 3 - totalPageLen; // format: (3/10)
        while (pos < words.length && currLength < maxWidth) {
            page.add(words[pos]);
            currLength += words[pos].length() + 1;
            pos++;
        }

        currLength--; // remove last blank space

        if (currLength > maxWidth) {
            currLength -= page.get(page.size() - 1).length();
            page.remove(page.size() - 1);
            pos--;
        }
        return new ReturnType(pos, page);
    }

    public class ReturnType {
        int nextPos;
        List<String> words;
        public ReturnType (int nextPos, List<String> words) {
            this.nextPos = nextPos;
            this.words = words;
        }
    }

    @Test
    public void test() {
        String str = "In the late 2000s to early 2010s, this feature was adopted more widely. Not only do many handsets support this feature, but support for the feature also exists amongst SMS gateway providers. ";
        System.out.println(splitMessage(str, 20));
    }
}
