package Uber;

/**
 * Created by siyuzhan on 5/20/16.
 */
import java.util.*;

public class UberPool {
    /**
     * 然后给了一个API。int  QueryEngine.getDistance(Point p1, Point p2).   这个API返回任意两个点之间所花费的用时, 用时O(1)。
     问题是： 给了初始乘客Requester r0 （知道他的起点， 终点），
     然后给一个 List<Requester>, 问这个list里的requester可以有多少人加入到current route里来？按顺序返回最终的route里的point
     */

    public List<Point> getRoute(Requester r0, List<Requester> list) {
        Point start = r0.start;
        Point end = r0.end;
        int totalTime = getDistance(start, end);

        return null;
    }

    public void helper(Point start, Point end, HashSet<Integer> visited, List<Requester> list, List<Point> path, List<List<Point>> result) {
        if (visited.size() == list.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (visited.contains(i)) {
                continue;
            }
            visited.add(i);
            Requester curr = list.get(i);
            Requester prev = list.get(i-1);

            // only consider 2 possible orders: prev|curr|curr|prev, prev|prev|curr|curr
            if (getDistance(prev.end, curr.start) + getDistance(curr.end, prev.end) + getDistance(curr.start, curr.end) <= getDistance(prev.start, prev.end)) {

            }
        }
    }

    public class Requester {
        Point start, end;
        public Requester(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }

    public class Point {
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int getDistance(Point a, Point b) {
        return 1;
    }
}
