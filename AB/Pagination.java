package Airbnb;

/**
 * Created by siyuzhan on 5/15/16.
 */
import java.util.*;
import org.junit.Test;
/*
 * Pagination: 给你一个list of posts，每个post对应一个host，这个list是已经排序好了的。
因为同一个host可以发好几个post，用户不希望看到Airbnb给的推荐房源都是来自同一个户主。所以面试官希望对这个list调整一下排序，让每一页里的post不出现相同的host，otherwise preserve the ordering。
每一页中有12个post。
 */
public class Pagination {
	/*
	 * The iterator approach is concise but the down side is it modifies the original input
	 */
    public List<Page> displayPages(List<String> input) {
        if (input == null || input.size() == 0) {
            return null;
        }

        // all distinct hosts in current page. Need to clear out when create new page
        HashSet<Integer> visited = new HashSet<>();
        Iterator<String> iterator = input.iterator();
        List<Page> pages = new ArrayList<>();
        Page curr = new Page(1, new ArrayList<String>());

        while (iterator.hasNext()) {
            String str = iterator.next();
            int hostId = Integer.parseInt(str.split(",")[0]);
            // If there's duplicate, not add to page but would still remain in iterator
            if (curr.list.size() < 12 && !visited.contains(hostId)) {
                curr.list.add(str);
                visited.add(hostId);
                // if added to page. remove from iterator so that would not be added ever
                iterator.remove();
            }

            if (curr.list.size() == 12 || !iterator.hasNext()) {
                pages.add(curr);
                curr = new Page(curr.id + 1, new ArrayList<String>());
                visited.clear();
                // This is very important here. By reassigning the iterator, we can pick up 
                // the left over duplicate hosts in the previous page. 
                iterator = input.iterator();
            }
        }

        // not add empty page but page with some contents
        if (curr.list.size() > 0) {
            pages.add(curr);
        }
        return pages;
    }

    public class Page {
        int id;
        List<String> list;
        public Page(int id, List<String> list) {
            this.id = id;
            this.list = list;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Page: " + id + "\n");
            for (String str: list) {
                sb.append(str + "\n");
            }
            return sb.toString();
        }
    }
    
    /*
     * Python code that does not use iterator
     * 
    items = [s.split(',') for s in a]
    k = 12
    # start is the index of the current page
    start = 0
    # pages is list of pages, each page is a list of posts
    pages = []
    # maps hostid to the page number it should go to
    hashmap = {}
    for item in items:
        if item[0] not in hashmap:
            # if host id not in map, update the page number to be current page
            hashmap[item[0]] = start
        if hashmap[item[0]] == len(pages):
            # if to insert the current item, we need a new page, then create a new page
            pages.append([])
        # add item to the page it belongs
        pages[hashmap[item[0]]].append(','.join(item))
        # for the next duplicate, should go to the next page
        hashmap[item[0]] += 1
        # if current page reached size, need to update current page
        if len(pages[start]) == k:
            start += 1
    for page in pages:
        print '--- page ---'
        for line in page:
            print line
     */

    @Test
    public void test() {
        String[] strs = new String[]{
                "1,28,300.1,SanFrancisco",
                "4,5,209.1,SanFrancisco",
                "20,7,208.1,SanFrancisco",
                "23,8,207.1,SanFrancisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,SanFrancisco",
                "6,29,204.1,SanFrancisco",
                "7,20,203.1,SanFrancisco",
                "8,21,202.1,SanFrancisco",
                "2,18,201.1,SanFrancisco",
                "2,30,200.1,SanFrancisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "1,2,103.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,SanJose",
                "6,25,10.1,Oakland",
                "19,15,9.1,SanJose",
                "3,19,8.1,SanJose",
                "3,11,7.1,Oakland",
                "27,12,6.1,Oakland",
                "1,3,5.1,Oakland",
                "25,4,4.1,SanJose",
                "5,6,3.1,SanJose",
                "29,22,2.1,SanJose",
                "30,23,1.1,SanJose"
        };
        List<String> input = new ArrayList<>(Arrays.asList(strs));
        System.out.println(displayPages(input));
    }
}
