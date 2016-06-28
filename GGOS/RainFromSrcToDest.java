package Google;/**
 * Created by siyuzhan on 6/12/16.
 */

import java.util.*;

import org.junit.Test;

public class RainFromSrcToDest {
    /**
     * 题目就是给你一个matrix，里面的数字代表bar的高度，现在说降雨量如果高于bar的高度水可以漫过去，降雨量0开始每天+1这样，问最早第几天水可以有一条路径从src漫到dst
     * 在所有possible paths中找一条的maxHeight是最小的
     * Dijkstra: priorityQueue of maxHeight - minimum maxHeight needed going from src to current
     */

    public int numDays(int[][] matrix, int srow, int scol, int drow, int dcol) {
        PriorityQueue<Cell> queue = new PriorityQueue<>(10, new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.maxHeight - o2.maxHeight;
            }
        });
        HashMap<Integer, Cell> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == srow && j == scol) {
                    Cell c = new Cell(i, j, matrix[i][j]);
                    queue.offer(c);
                    map.put(i * matrix[0].length + j, c);
                } else {
                    Cell c = new Cell(i, j, Integer.MAX_VALUE);
                    queue.offer(c);
                    map.put(i * matrix[0].length + j, c);
                }
            }
        }
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            if (curr.row == drow && curr.col == dcol) {
                break;
            }
            map.remove(curr.row * matrix[0].length + curr.col);
            for (int i = 0; i < 4; i++) {
                int nx = curr.row + dx[i];
                int ny = curr.col + dy[i];
                if (0 <= nx && nx < matrix.length && 0 <= ny && ny < matrix[0].length) {
                    if (!map.containsKey(nx * matrix[0].length + ny)) {
                        continue;
                    }
                    Cell next = map.get(nx * matrix[0].length + ny);
                    if (next.maxHeight == Integer.MAX_VALUE) {
                        next.maxHeight = Math.max(matrix[next.row][next.col], curr.maxHeight);
                        queue.remove(next);
                        queue.offer(next);
                    } else {
                        next.maxHeight = Math.max(matrix[next.row][next.col], Math.min(next.maxHeight, curr.maxHeight));
                        queue.remove(next);
                        queue.offer(next);
                    }
                }
            }
        }
        return map.get(drow * matrix[0].length + dcol).maxHeight;
    }

    public class Cell {
        int row, col, maxHeight;
        public Cell (int row, int col, int maxHeight) {
            this.row = row;
            this.col = col;
            this.maxHeight = maxHeight;
        }
        public String toString() {
            return "row: " + row + " col: " + col + " maxHeight: " + maxHeight;
        }
    }

    @Test
    public void test() {
        int[][] matrix = {
                {1, 0, 0, 0, 0},
                {0, 0, 2, 0, 0},
                {3, 1, 0, 0, 0}
        };
        System.out.println(numDays(matrix, 0, 1, 2, 2));
    }

}
