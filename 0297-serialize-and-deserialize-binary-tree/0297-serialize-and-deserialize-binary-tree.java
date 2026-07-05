/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }

    void helper(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("null,");
            return;
        }
        sb.append(root.val).append(",");
        helper(root.left, sb);
        helper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String arr[] = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(arr));
        return buildTree(queue);
    }

    TreeNode buildTree(Queue<String> q){
        String val = q.poll();
        if(val.equals("null")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(q);
        node.right = buildTree(q);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));