package Snapchat; /**
 * Created by siyuzhan on 4/30/16.
 */
import java.util.*;
/*
 * 给了一个Person class， 有score和nextSnap两个属性，每个Person有一个朋友的list，也就是nextSnap，输入是（Person， maxStep）， 
 * 在maxStep步数以内算max score，注意的是下一个Friend可能会指向上一个Person，要注意回溯的问题.
 */
public class PersonSnaps {
    public void test() {
        Person p1 = new Person(1, 10, "a");
        Person p2 = new Person(2, 5, "b");
        Person p3 = new Person(3, 5, "c");
        Person p4 = new Person(4, 15, "d");
        p1.nextSnaps.add(p2);
        p2.nextSnaps.add(p3);
        p1.nextSnaps.add(p4);
        p4.nextSnaps.add(p3);
        p3.nextSnaps.add(p1);
        System.out.println(maxScore(p1, 1));
        System.out.println(getFriends(p1));
    }

    // TODO: This code is not right. Should not have visited set since we can revisit. only stop when step reaches limit.
    // also have max score as a global or a modified value
    public int maxScore(Person p, int step) {
        HashSet<Integer> visited = new HashSet<>();
        visited.add(p.id);
        return helper(p, step, visited, 0);
    }

    public int helper(Person p, int step, HashSet<Integer> visited, int prevScore) {
        if (step <= 0 ) {
            //System.out.println(visited);
            return prevScore;
        }
        int maxScore = prevScore;
        for (Person next: p.nextSnaps) {
            if (!visited.contains(next.id)) {
                visited.add(next.id);
                //System.out.println("Adding next person: " + next.id);
                maxScore = Math.max(maxScore, helper(next, step - 1, visited, prevScore + next.score));
                visited.remove(next.id);
                //System.out.println("Removing next person: " + next.id);
            }
        }
        return maxScore;
    }

    public List<String> getFriends(Person p) {
        if (p == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        Queue<Person> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(p);
        visited.add(p.name);
        while (!queue.isEmpty()) {
            Person curr = queue.poll();
            result.add(curr.name);
            for (Person next: curr.nextSnaps) {
                if (!visited.contains(next.name)) {
                    queue.offer(next);
                    visited.add(next.name);
                }
            }
        }
        return result;
    }

    public class Person {
        public int id, score;
        public String name;
        public List<Person> nextSnaps;
        public Person(int id, int score, String name) {
            this.id = id;
            this.score = score;
            this.name = name;
            this.nextSnaps = new ArrayList<>();
        }
    }

}
