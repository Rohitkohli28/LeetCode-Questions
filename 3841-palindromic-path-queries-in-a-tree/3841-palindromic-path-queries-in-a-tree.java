import java.util.*;

class Solution {

    private int n, LOG, timer;

    private List<Integer>[] adj;

    private int[] tin, tout, depth;
    private int[][] up;

    private char[] chars;

    private Fenwick bit;

    class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            while (i < tree.length) {
                tree[i] ^= val;
                i += i & -i;
            }
        }

        int query(int i) {
            int res = 0;

            while (i > 0) {
                res ^= tree[i];
                i -= i & -i;
            }

            return res;
        }

        void rangeUpdate(int l, int r, int val) {
            update(l, val);
            update(r + 1, val);
        }

        int pointQuery(int i) {
            return query(i);
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges,
                                    String s, String[] queries){
        this.n = n;
        this.LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        chars = s.toCharArray();

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[LOG][n];

        buildTree();

        bit = new Fenwick(n + 1);

        // Initialize root-to-node character masks.
        for (int u = 0; u < n; u++) {
            int mask = 1 << (chars[u] - 'a');
            bit.rangeUpdate(tin[u], tout[u], mask);
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {

            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {

                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');

                int diff = oldMask ^ newMask;

                if (diff != 0) {
                    bit.rangeUpdate(tin[u], tout[u], diff);
                    chars[u] = c;
                }

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = findLCA(u, v);

                int maskU = bit.pointQuery(tin[u]);
                int maskV = bit.pointQuery(tin[v]);

                int lcaMask = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaMask;

                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }

       return result;
    }

    private void buildTree() {

        timer = 0;

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        // Iterative DFS using entry and exit events.
        int[] stackNode = new int[2 * n + 5];
        int[] stackParent = new int[2 * n + 5];
        boolean[] exit = new boolean[2 * n + 5];

        int top = 0;

        stackNode[top] = 0;
        stackParent[top] = 0;
        exit[top++] = false;

        while (top > 0) {

            top--;

            int u = stackNode[top];
            int p = stackParent[top];
            boolean isExit = exit[top];

            if (isExit) {
                tout[u] = timer;
                continue;
            }

            parent[u] = p;
            depth[u] = (u == p) ? 0 : depth[p] + 1;

            up[0][u] = p;

            for (int j = 1; j < LOG; j++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }

            tin[u] = ++timer;

            // Process the exit event after all children.
            stackNode[top] = u;
            stackParent[top] = p;
            exit[top++] = true;

            for (int v : adj[u]) {
                if (v == p) {
                    continue;
                }

                stackNode[top] = v;
                stackParent[top] = u;
                exit[top++] = false;
            }
        }
    }

    private int findLCA(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}