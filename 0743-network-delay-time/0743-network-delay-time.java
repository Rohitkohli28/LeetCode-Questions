class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new int[] { time[1], time[2] });
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dist[k] = 0;
        pq.offer(new int[] { 0, k });
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int d = node[0];
            int vertex = node[1];
            if (d > dist[vertex])
                continue;
            for (int[] neighbor : graph.get(vertex)) {
                int next = neighbor[0];
                int weight = neighbor[1];
                if (dist[vertex] + weight < dist[next]) {
                    dist[next] = dist[vertex] + weight;
                    pq.offer(new int[] { dist[next], next });
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}