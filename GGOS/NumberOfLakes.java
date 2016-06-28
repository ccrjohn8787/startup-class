package Google;/**
 * Created by siyuzhan on 6/6/16.
 */

import java.util.*;

import org.junit.Test;

public class NumberOfLakes {
    /**
     * 如何找湖的数量呢？湖的定义是某个0，其上下左右都是同一个岛屿的陆地。
     */
    public int numberOfLakes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        // mark islands
        int islandNum = 2;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    dfs(matrix, i, j, islandNum);
                    islandNum++;
                }
            }
        }

        Util.Utilities.print(matrix);
        // find lakes
        int numLakes = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (dfsLake(matrix, i, j, 0)) {
                        numLakes++;
                    }
                }
            }
        }

        return numLakes;
    }

    public void dfs(int[][] matrix, int row, int col, int islandNum) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] != 1) {
            return;
        }
        matrix[row][col] = islandNum;
        dfs(matrix, row + 1, col, islandNum);
        dfs(matrix, row - 1, col, islandNum);
        dfs(matrix, row, col + 1, islandNum);
        dfs(matrix, row, col - 1, islandNum);
    }

    public boolean dfsLake(int[][] matrix, int row, int col, int islandId) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        matrix[row][col] = -1;
        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];
            if (0 <= nx && nx < matrix.length && 0 <= ny && ny < matrix[0].length) {
                if (matrix[nx][ny] == 0 && !dfsLake(matrix, nx, ny, islandId)) {
                    return false;
                } else {
                    if (islandId == 0) {
                        islandId = matrix[nx][ny];
                    } else if (matrix[nx][ny] != islandId) {
                        return false;
                    }
                }
            } else {
                // connects to ocean
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[][] matrix = {
                {0, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0},
                {1, 0, 0, 0, 1},
        };
        System.out.println(numberOfLakes(matrix));
    }
}
