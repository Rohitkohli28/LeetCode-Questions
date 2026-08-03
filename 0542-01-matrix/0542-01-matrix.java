class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        int[][] distance = new int[rows][cols];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 1 : Push all 0's
        for(int i=0; i < rows; i++){
            for(int j = 0; j< cols; j++){

                if(mat[i][j] == 0){
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // Multi sourse BFS

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for(int k = 0; k < 4; k++){
                int newRow = r + dr[k];
                int newCol = c + dc[k];

                if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol]){
                    visited[newRow][newCol] = true;

                    distance[newRow][newCol] = distance[r][c] + 1;

                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
        return distance;
    }
}