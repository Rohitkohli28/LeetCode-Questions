class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litterId = new int[m][n];

        int startRow = 0;
        int startCol = 0;
        int litterCount = 0;

        // Find S and assign an ID to every L
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startRow = r;
                    startCol = c;
                }
                else if (ch == 'L') {
                    litterId[r][c] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;

        /*
         * visited[row][col][energy][mask]
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        int initialMask = (1 << litterCount) - 1;

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {
            startRow,
            startCol,
            energy,
            initialMask
        });

        visited[startRow][startCol][energy][initialMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int curEnergy = state[2];
                int mask = state[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // No energy means we cannot make another move
                if (curEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char nextCell = classroom[nr].charAt(nc);

                    // Calculate next energy
                    int nextEnergy;

                    if (nextCell == 'R') {
                        nextEnergy = energy;
                    } else {
                        nextEnergy = curEnergy - 1;
                    }

                    // Update litter mask
                    int nextMask = mask;

                    if (nextCell == 'L') {
                        int id = litterId[nr][nc];

                        nextMask &= ~(1 << id);
                    }

                    // Avoid repeated states
                    if (!visited[nr][nc][nextEnergy][nextMask]) {

                        visited[nr][nc][nextEnergy][nextMask] = true;

                        queue.offer(new int[] {
                            nr,
                            nc,
                            nextEnergy,
                            nextMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}