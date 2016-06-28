package Google;/**
 * Created by siyuzhan on 6/6/16.
 */

import java.util.*;

import org.junit.Test;

public class TiltMaze {
    /**
     * 1.写一个数据结构来记录这个Maze
     2.找到一条从起点s到终点e的路径，要求使用最少的move

     3.找到一条从起点s到终点e的路径，要求使用距离最少
     */
    Cell[][] grid;
    public TiltMaze(int row, int col) {
        grid = new Cell[row][col];

    }

    public int minMove(int srow, int scol, int erow, int ecol) {
        Cell start = grid[srow][scol];
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(start);
        visited[srow][scol] = true;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Cell curr = queue.poll();
                if (curr.row == erow && curr.col == ecol) {
                    return steps;
                }
                steps++;
                for (int i = 0; i < 4; i++) {
                    if (curr.canGo[i]) {
                        int nx = curr.row;
                        int ny = curr.col;
                        while (0 <= nx && nx < grid.length && 0 <= ny && ny < grid[0].length && grid[nx][ny].canGo[i]) {
                            nx += dx[i];
                            ny += dy[i];
                        }
                        if (!visited[nx][ny]) {
                            queue.offer(grid[nx][ny]);
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public int minDist(int srow, int scol, int erow, int ecol) {
        Cell start = grid[srow][scol];
        PriorityQueue<Cell> dist = new PriorityQueue<>(10, new Comparator<Cell>(){
            public int compare(Cell a, Cell b) {
                return a.dist - b.dist;
            }
        });
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i != srow && j != scol) {
                    grid[i][j].dist = Integer.MAX_VALUE;
                }
                dist.offer(grid[i][j]);
            }
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!dist.isEmpty()) {
            Cell curr = dist.poll();
            if (curr.row == erow && curr.col == ecol) {
                return curr.dist;
            }
            for (int i = 0; i < 4; i++) {
                if (curr.canGo[i]) {
                    int nx = curr.row;
                    int ny = curr.col;
                    int len = 0;
                    while (0 <= nx && nx < grid.length && 0 <= ny && ny < grid[0].length && grid[nx][ny].canGo[i]) {
                        nx += dx[i];
                        ny += dy[i];
                        len++;
                    }
                    if (curr.dist + len < grid[nx][ny].dist) {
                        grid[nx][ny].dist = curr.dist + len;
                        dist.remove(grid[nx][ny]);
                        dist.add(grid[nx][ny]);
                    }
                }
            }
        }
        return -1;
    }
}

class Cell {
    int row, col, dist;
    boolean[] canGo; // up, down, left, right true = can keep going if coming from the direction
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.dist = 0;
        this.canGo = new boolean[4];
    }
}
