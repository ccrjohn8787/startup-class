package Google;
import java.util.*;
import org.junit.Test;
import org.junit.Assert;
/*
 * 给出一个 list of int, the target, 输出这个 list 中所用的数能否通过4则运算 得到 target。
boolean EvaluatesTo(list numbers, int target)
［2 3 6 9］ → 75(2+3)*(6+9) = 75 return true
［2 3 6 9］ －> 11 return false
 */
public class EvaluateExpression {
	// Approach: First do permutationsII to get all permutations of number
	//           Then divide and conquer get all possible combinations of number
    boolean evaluatesTo(List<Integer> numbers, int target) {
        if (numbers == null || numbers.size() == 0) {
            return false;
        }
        // get all permutations
        List<List<Integer>> allPerms = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        Collections.sort(numbers);

        getPermutations(numbers, path, allPerms, visited);

        // partition list of numbers and add operators
        for (List<Integer> perm: allPerms) {
            List<Integer> possibleResults = getResult(perm, 0, perm.size()-1);
            for (Integer result: possibleResults) {
                if (result == target) {
                    return true;
                }
            }
        }

        return false;
    }

    void getPermutations(List<Integer> numbers, List<Integer> path, List<List<Integer>> result,
                         HashSet<Integer> visited) {
        if (path.size() == numbers.size()) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < numbers.size(); i++) {
            if (visited.contains(i) || (i != 0 && numbers.get(i) == numbers.get(i - 1) && !visited.contains(i-1))) {
                continue;
            }
            path.add(numbers.get(i));
            visited.add(i);
            getPermutations(numbers, path, result, visited);
            path.remove(path.size()-1);
            visited.remove(i);
        }
    }

    // divide and conquer get all possible numbers between start and end
    List<Integer> getResult(List<Integer> numbers, int start, int end) {
        List<Integer> result = new ArrayList<>();
        if (start > end || numbers.size() == 0) {
            return result;
        }
        if (start == end) {
            result.add(numbers.get(start));
            return result;
        }

        for (int i = start; i < end; i++) {
            List<Integer> left = getResult(numbers, start, i);
            List<Integer> right = getResult(numbers, i+1, end);
            for (Integer l: left) {
                for (Integer r: right) {
                    result.add(l + r);
                    result.add(l - r);
                    result.add(l * r);
                    if (r != 0 && l%r == 0) {
                        result.add(l/r);
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(2);
        //list.add(6);
        list.add(100);
        System.out.println(evaluatesTo(list, 25));
    }
}
