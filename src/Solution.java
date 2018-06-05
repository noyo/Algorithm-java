class Solution {

    int min = Integer.MAX_VALUE;

    void dfs(int graph[][], boolean vst[][], int start, int len, boolean visit[], int n) {
        if (n == graph.length) {
            min = Math.min(min, len);
            return;
        }
        int edges[] = graph[start];
        for (int i = 0; i < edges.length; i++) {
            if (start == edges[i] || vst[start][edges[i]]) {
                continue;
            }
            vst[start][edges[i]] = true;
            if (!visit[edges[i]]) {
                visit[edges[i]] = true;
                n++;
            }
            dfs(graph, vst, edges[i], len + 1, visit, n);

            if (visit[edges[i]] && !vst[edges[i]][start]) {
                visit[edges[i]] = false;
                n--;
            }
            vst[start][edges[i]] = false;
        }
    }

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if (n < 1) {
            return n;
        }
        for (int i = 0; i < n; i++) {
            boolean vst[][] = new boolean[n][n];
            boolean visit[] = new boolean[n];
            visit[i] = true;
            dfs(graph, vst, i, 0, visit, 1);
        }

        if (min == Integer.MAX_VALUE) {
            min = 0;
        }
        return min;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
//        int graph[][] = new int[][]{{1,2,3},{0},{0},{0}};
        int graph[][] = new int[][]{{1},{0,2,4},{1,3,4},{2},{1,2}};
        System.out.println(s.shortestPathLength(graph));
    }
}