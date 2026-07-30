class Solution {
    public int closedIsland(int[][] grid) {
         int rows = grid.length;
        int cols = grid[0].length;

        // Remove boundary islands
        for (int i = 0; i < rows; i++) {

            if (grid[i][0] == 0)
                dfs(grid, i, 0);

            if (grid[i][cols - 1] == 0)
                dfs(grid, i, cols - 1);
        }

        for (int j = 0; j < cols; j++) {

            if (grid[0][j] == 0)
                dfs(grid, 0, j);

            if (grid[rows - 1][j] == 0)
                dfs(grid, rows - 1, j);
        }

        int closedIslands = 0;

        for (int i = 1; i < rows - 1; i++) {

            for (int j = 1; j < cols - 1; j++) {

                if (grid[i][j] == 0) {

                    dfs(grid, i, j);

                    closedIslands++;
                }
            }
        }

        return closedIslands;
    }
    private void dfs(int[][] grid, int row, int col) {

    if (row < 0 || col < 0 ||
        row >= grid.length ||
        col >= grid[0].length ||
        grid[row][col] == 1) {
        return;
    }

    grid[row][col] = 1;

    dfs(grid, row - 1, col);
    dfs(grid, row + 1, col);
    dfs(grid, row, col - 1);
    dfs(grid, row, col + 1);
}
}