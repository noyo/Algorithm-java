package format;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/7 15:47
 * @see format
 */
public class Dijkstra {

    private static class Edge {
        int to;
        int w;

        Edge(int t, int ww) {
            to = t;
            w = ww;
        }

        @Override
        public String toString() {
            return to + "," + w;
        }
    }

//    private static int[] dijkstra(int from, int to, List<Edge> edges[], int s[]) {
//        int n = edges.length;
//        int dist[] = new int[n];
//        int pre[] = new int[n];
//        boolean vst[] = new boolean[n];
//        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> dist[o]));
//        int INF = Integer.MAX_VALUE;
//        Arrays.fill(pre, -1);
//        Arrays.fill(dist, INF);
//
//        for (int i = 0; i < edges[from].size(); i++) {
//            int t = edges[from].get(i).to;
//            dist[t] = edges[from].get(i).w;
//            pre[t] = 0;
//            queue.offer(t);
//        }
//
//        vst[from] = true;
//        vst[to] = true;
//        while (!queue.isEmpty()) {
//            int i = queue.poll();
//            if (vst[i]) {
//                continue;
//            }
//            vst[i] = true;
//            for (int j = 0; j < edges[i].size(); j++) {
//                Edge edge = edges[i].get(j);
//                if (dist[i] + edge.w < dist[edge.to]) {
//                    dist[edge.to] = dist[i] + edge.w;
//                    pre[edge.to] = i;
//                    queue.remove(edge.to);
//                    queue.offer(edge.to);
//                }
//            }
//        }
//        return pre;
//    }
}
