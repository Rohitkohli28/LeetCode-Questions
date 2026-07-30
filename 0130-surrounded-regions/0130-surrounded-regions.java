class Solution {

    private void dfs(char[][] board, int row, int col) {

        if (row < 0 || col < 0 ||
            row >= board.length ||
            col >= board[0].length ||
            board[row][col] != 'O') {
            return;
        }

        board[row][col] = 'S';

        dfs(board, row - 1, col);
        dfs(board, row + 1, col);
        dfs(board, row, col - 1);
        dfs(board, row, col + 1);
    }

    public void solve(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        // First and Last Column
        for (int i = 0; i < rows; i++) {

            if (board[i][0] == 'O')
                dfs(board, i, 0);

            if (board[i][cols - 1] == 'O')
                dfs(board, i, cols - 1);
        }

        // First and Last Row
        for (int j = 0; j < cols; j++) {

            if (board[0][j] == 'O')
                dfs(board, 0, j);

            if (board[rows - 1][j] == 'O')
                dfs(board, rows - 1, j);
        }

        // Convert remaining cells
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (board[i][j] == 'O')
                    board[i][j] = 'X';

                else if (board[i][j] == 'S')
                    board[i][j] = 'O';
            }
        }
    }
}