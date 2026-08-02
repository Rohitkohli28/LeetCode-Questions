class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        bfs(0, rooms, visited);
        for(boolean ele : visited){
            if(ele == false) return false;
        }
        return true;
    }

    private void bfs(int start, List<List<Integer>> rooms, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>(); 
        queue.add(start);
        while(queue.size() > 0){
            int front = queue.remove();
            for(int ele : rooms.get(front)){
                if(!visited[ele]){
                    visited[ele] = true;
                    queue.add(ele);
                }
            }
        }
    }
}