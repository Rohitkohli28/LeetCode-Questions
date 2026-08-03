class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<ArrayList<Integer>>  graph = new ArrayList<>();

        // Step 2 : Initailize each vertex with an empty list
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        // Step 3: Build a grpah
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Step 4: Create queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Step 5: Viisted Array
        boolean[] visited = new boolean[n];
        queue.add(source);
        visited[source] = true;


        // Step 6 : BFS traversal
        while(!queue.isEmpty()){
            int node = queue.poll();

            // Destination found
            if(node == destination){
                return true;
            }

            // Visit all neighbour
            for(int neighbour : graph.get(node)){
                if(!visited[neighbour]){
                    visited[neighbour] = true;
                    queue.offer(neighbour);
                }
            }
        }
        return false;
    }
}