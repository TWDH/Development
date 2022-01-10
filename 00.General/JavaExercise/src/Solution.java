import java.util.Arrays;

public class Solution {
    public static int[][] updateMatrix(int[][] matrix) {
        //行列数量
        int numRow = matrix.length;
        int numCol = matrix[0].length;
        //2.遍历每个点
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                int distance = 0;
                //3.如果当前点就是0
                if (matrix[i][j] == 0) {
                    continue;
                }
                //4.开启dfs返回
                int[][] visited = new int[numRow][numCol];
                distance = dfs(matrix, i, j, 0, visited);
                matrix[i][j] = distance;
            }
        }

        return matrix;
    }

    public static int dfs(int[][] matrix, int row, int col, int dis, int[][] visited) {
        //当找到0时返回
        if (matrix[row][col] == 0) {
            return dis;
        }
        visited[row][col] = 1;
        int minDistance = Integer.MAX_VALUE;
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        //遍历上下左右
        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            // 范围内
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length) {
                // 已经标记 visited
                if (visited[newRow][newCol] == 1) {
                    continue;
                }
                minDistance = Math.min(minDistance, dfs(matrix, newRow, newCol, dis + 1, visited));
                // 因为visited标记过的，一个点不会遍历，所以需要每次dfs执行完重置
                visited[newRow][newCol] = 0;
            }
        }
        return minDistance;
    }

    public static String hello() {
        return "hello world";
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 1, 1, 1, 1}
        };

        System.out.println("======================================");
        String hello = hello();
        System.out.println(hello);
        int[][] result = updateMatrix(matrix);

        System.out.println(Arrays.toString(result));
        System.out.println("------------------------------------");

    }
}
