package Uber;

/**
 * Created by siyuzhan on 5/20/16.
 */
import java.util.*;

public class HotelRooms {
    /**
     * http://www.1point3acres.com/bbs/forum.php?mod=redirect&goto=findpost&ptid=167789&pid=2189417&fromuid=206597
     *
     *
     */

    // Idea:
    // Naive idea: use dfs to find all possible arrangements and sort by length. Could potantially early terminate
    //              for (intervals): if (hasOverlap) {recurse on if change room;} {recurse on if not change room}

    // Better: bfs + dfs
    // build graph: if (2 intervals have overlap), there's an edge from earlier interval to later interval
    // bfs until reach end of date and remember each prev steps (like word ladder)
    // then dfs back
    public List<List<Interval>> getAllMinArrangements(List<Interval> availableIntervals, Interval range) {
        List<List<Interval>> result = new ArrayList<>();

        if (availableIntervals == null || availableIntervals.size() == 0) {
            return result;
        }

        Collections.sort(availableIntervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        for (Interval i: availableIntervals) {
            if (i.end <= range.start) {
                continue;
            }

        }
        return result;
    }

    public void helper(List<Interval> intervals, Interval range, List<Interval> candidate, List<List<Interval>> result) {

    }

    public class Interval {
        int start;
        int end;
        int roomId;
        public Interval(int start, int end, int roomId) {
            this.start = start;
            this.end = end;
            this.roomId = roomId;
        }
    }

    public class Schedule {
        Interval interval;
        List<Interval> prev;
        public Schedule(Interval interval) {
            this.interval = interval;
            this.prev = new ArrayList<>();
        }
    }
}
