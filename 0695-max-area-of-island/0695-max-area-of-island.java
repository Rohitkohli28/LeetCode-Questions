class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[0].length; col++) {

                if (grid[row][col] == 1) {

                    int area = bfs(grid, row, col);

                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int bfs(int[][] grid, int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        grid[row][col] = 0;

        int area = 1;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            for(int i=0; i<4; i++){
                int newRow = current[0] + dr[i];
                int newCol = current[1] + dc[i];

                if(newRow >= 0 && newCol >= 0 && newRow < grid.length &&
                newCol < grid[0].length &&
                grid[newRow][newCol] == 1){
                    queue.offer(new int[]{newRow, newCol});
                grid[newRow][newCol] = 0;
                area++;
                }
            } 
        }
    return area;
    }
}