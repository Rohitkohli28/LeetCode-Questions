class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [start, end, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by end time
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        // dp[i][j] = best result using first i intervals and choosing j intervals
        // Store selected indices as List<Integer>
        List<Integer>[][] dp = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                dp[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            int start = arr[i - 1][0];
            int weight = arr[i - 1][2];
            int originalIndex = arr[i - 1][3];

            // Don't take current interval
            for (int j = 1; j <= 4; j++) {
                dp[i][j] = new ArrayList<>(dp[i - 1][j]);
            }

            // Find last interval whose end < current start
            int prev = findPrevious(arr, i - 1, start);

            for (int j = 1; j <= 4; j++) {
                if (dp[prev + 1][j - 1].size() == j - 1) {

                    List<Integer> candidate =
                            new ArrayList<>(dp[prev + 1][j - 1]);

                    candidate.add(originalIndex);

                    if (isBetter(candidate, dp[i][j], intervals)) {
                        dp[i][j] = candidate;
                    }
                }
            }
        }

        // Find best among choosing 1..4 intervals
        List<Integer> answer = new ArrayList<>();

        for (int j = 1; j <= 4; j++) {
            if (isBetter(dp[n][j], answer, intervals)) {
                answer = dp[n][j];
            }
        }

        Collections.sort(answer);

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // Returns index of last interval with end < start
    private int findPrevious(int[][] arr, int right, int start) {
        int left = 0;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid][1] < start) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    // Compare two selections:
    // 1. Higher total weight
    // 2. If equal, lexicographically smaller indices
    private boolean isBetter(
            List<Integer> a,
            List<Integer> b,
            List<List<Integer>> intervals) {

        if (a.isEmpty()) return false;
        if (b.isEmpty()) return true;

        long weightA = 0;
        long weightB = 0;

        for (int idx : a) {
            weightA += intervals.get(idx).get(2);
        }

        for (int idx : b) {
            weightB += intervals.get(idx).get(2);
        }

        if (weightA != weightB) {
            return weightA > weightB;
        }

        List<Integer> x = new ArrayList<>(a);
        List<Integer> y = new ArrayList<>(b);

        Collections.sort(x);
        Collections.sort(y);

        for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i);
            }
        }

        return x.size() < y.size();
    }
}