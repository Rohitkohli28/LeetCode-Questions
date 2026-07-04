/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int time = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;

            for (int i = 0; i < size; i++) {
                int node = queue.poll();

                for (int nei : graph.getOrDefault(node, new ArrayList<>())) {
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        queue.offer(nei);
                    }
                }
            }
        }

        return time;
    }

    private void buildGraph(TreeNode node, TreeNode parent,
                            Map<Integer, List<Integer>> graph) {
        if (node == null) return;

        graph.putIfAbsent(node.val, new ArrayList<>());

        if (parent != null) {
            graph.get(node.val).add(parent.val);

            graph.putIfAbsent(parent.val, new ArrayList<>());
            graph.get(parent.val).add(node.val);
        }

        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }
}