class Solution {

    int n;
    long k;
    List<int[]>[] graph;

    private boolean check(int minEdge) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long d = cur[0];
            int u = (int) cur[1];

            if (d > dist[u]) continue;
            if (d > k) continue;

            if (u == n - 1) return true;

            for (int[] e : graph[u]) {
                int v = e[0];
                int w = e[1];

                if (w < minEdge) continue;

                long nd = d + w;

                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{nd, v});
                }
            }
        }

        return false;
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        this.k = k;
        n = online.length;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int low = Integer.MAX_VALUE;
        int high = 0;

        for (int[] e : edges) {

            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (!online[u] || !online[v])
                continue;

            graph[u].add(new int[]{v, w});

            low = Math.min(low, w);
            high = Math.max(high, w);
        }

        if (low == Integer.MAX_VALUE)
            return -1;

        while (low < high) {

            int mid = low + (high - low + 1) / 2;

            if (check(mid))
                low = mid;
            else
                high = mid - 1;
        }

        return check(low) ? low : -1;
    }
}