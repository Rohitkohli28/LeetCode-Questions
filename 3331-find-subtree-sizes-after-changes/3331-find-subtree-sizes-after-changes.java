class Solution {

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();

        List<Integer>[] pathChars = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            pathChars[i] = new ArrayList<>();
        }

        dfsBuild(0, tree, s, pathChars, newParent);

        List<Integer>[] finalTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            finalTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            finalTree[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        dfsSize(0, finalTree, ans);

        return ans;
    }

    private void dfsBuild(
            int u,
            List<Integer>[] tree,
            String s,
            List<Integer>[] pathChars,
            int[] newParent) {

        int c = s.charAt(u) - 'a';

        if (u != 0 && !pathChars[c].isEmpty()) {
            newParent[u] = pathChars[c].get(pathChars[c].size() - 1);
        }

        pathChars[c].add(u);

        for (int v : tree[u]) {
            dfsBuild(v, tree, s, pathChars, newParent);
        }

        pathChars[c].remove(pathChars[c].size() - 1);
    }

    private int dfsSize(int u, List<Integer>[] tree, int[] ans) {
        int size = 1;

        for (int v : tree[u]) {
            size += dfsSize(v, tree, ans);
        }

        ans[u] = size;
        return size;
    }
}