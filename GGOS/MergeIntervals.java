package Google; /**
 * Created by siyuzhan on 5/9/16.
 */
import java.util.*;
import org.junit.Test;

public class MergeIntervals {

    public List<Interval> mergeIntervals(List<Interval> list) {
        List<Interval> result = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return result;
        }
        Collections.sort(list, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        Interval last = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Interval curr = list.get(i);
            if (curr.start <= last.end) {
                last.end = Math.max(last.end, curr.end);
            } else {
                result.add(last);
                last = curr;
            }
        }
        result.add(last);
        return result;
    }

    public List<Interval> addInterval(List<Interval> list, Interval curr) {
        list = mergeIntervals(list);
        System.out.println(list);
        int i = 0;
        int j = list.size() - 1;
        while (i + 1 < j) {
            int mid = (i + j)/2;
            if (list.get(mid).end >= curr.start) {
                j = mid;
            } else {
                i = mid;
            }
        }
        int firstOverlap = 0;
        if (list.get(i).end >= curr.start) {
            firstOverlap = i;
        } else {
            firstOverlap = j;
        }

        i = 0;
        j = list.size() - 1;
        while (i + 1 < j) {
            int mid = (i + j)/2;
            if (list.get(mid).start <= curr.end) {
                i = mid;
            } else {
                j = mid;
            }
        }
        int lastOverlap = 0;
        if (list.get(j).start <= curr.end) {
            lastOverlap = j;
        } else {
            lastOverlap = i;
        }

        Interval newInterval = new Interval(Math.min(curr.start, list.get(firstOverlap).start), Math.max(curr.end, list.get(lastOverlap).end));
        for (int k = firstOverlap; k<= lastOverlap; k++) {
            list.remove(firstOverlap);
        }
        System.out.println(list);
        list.add(firstOverlap, newInterval);
        return list;
    }

    public class Interval {
        int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    @Test
    public void test() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 2));
        list.add(new Interval(3, 5));
        list.add(new Interval(4, 6));
        list.add(new Interval(7, 8));
        list.add(new Interval(9, 11));

        System.out.println(addInterval(list, new Interval(5, 10)));
    }
}
