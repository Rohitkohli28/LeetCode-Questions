class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int distance = 1;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1) {
            return -1;
        }

        queue.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };

        int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                int r = current[0];
                int c = current[1];

                if (r == rows - 1 && c == cols - 1) {
                    return distance;
                }

                // Explore every direction
                for (int k = 0; k < 8; k++) {
                    int newRow = r + dr[k];
                    int newCol = c + dc[k];

                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 0
                            && !visited[newRow][newCol]) {
                        if (!visited[newRow][newCol]) {
                            visited[newRow][newCol] = true;
                            queue.offer(new int[] { newRow, newCol });
                        }
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}