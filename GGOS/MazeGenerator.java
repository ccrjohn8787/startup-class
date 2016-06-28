package Google;/**
 * Created by siyuzhan on 6/18/16.
 */

import java.util.*;

import org.junit.Test;

public class MazeGenerator {
    public Cell[][] generate(int n) {
        Cell[][] grid = new Cell[n][n];
        HashSet<Cell> visited = new HashSet<>();
        dfs(grid, 0, 0, visited);
        return grid;
    }

    public void dfs(Cell[][] grid, int row, int col, HashSet<Cell> visited) {
        visited.add(grid[row][col]);
        if (visited.size() == grid.length * grid[0].length) {
            return;
        }
        Integer[] dx = {0, 0, 1, -1};
        Integer[] dy = {1, -1, 0, 0};
        List<Integer> dxl = Arrays.asList(dx);
        List<Integer> dyl = Arrays.asList(dy);
        Collections.shuffle(dxl);
        Collections.shuffle(dyl);
        for (int i = 0; i < 4; i++) {
            int nx = row + dxl.get(i);
            int ny = col + dyl.get(i);
            if (0 <= nx && nx < grid.length && 0 <= ny && ny < grid[0].length && !visited.contains(grid[nx][ny])) {
                //remove corresponding wall from both cells

                dfs(grid, nx, ny, visited);
            }
        }
    }

    public class Cell {
        int row, col;
        boolean[] walls; // right, left, down, up
        public Cell (int row, int col) {
            this.row = row;
            this.col = col;
            this.walls = new boolean[4];
            for (int i = 0; i < 4; i++) {
                this.walls[i] = true; // initially all 4 sides are walls
            }
        }
    }

    @Test
    public void test()
    {
        generate(4);
    }
}
