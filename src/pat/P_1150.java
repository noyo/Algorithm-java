package pat;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 14:10
 * @see pat
 */
public class P_1150 {
    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    private static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        int dist[][] = new int[201][201];
        for (int i = 0; i < m; i++) {
            int from = nextInt();
            int to = nextInt();
            int w = nextInt();
            dist[from][to] = w;
        }
        int k = nextInt();
        int min = Integer.MAX_VALUE;
        int minI = 0;
        Set<Integer> set = new HashSet<>(n);
        for (int i = 1; i <= k; i++) {
            int curDist = 0;
            boolean ok = true;
            boolean simple = true;
            int kk = nextInt();
            int start = nextInt();
            int next = start;
            int pre = start;
            set.clear();
            set.add(start);
            for (int j = 1; j < kk; j++) {
                next = nextInt();
                int val = dist[pre][next] == 0 ? dist[next][pre] : dist[pre][next];
                if (set.contains(next) && j != kk - 1) {
                    simple = false;
                }
                if (val > 0) {
                    curDist += val;
                } else {
                    ok = false;
                    nextLine();
                    break;
                }
                set.add(next);
                pre = next;
            }
            pw.format("Path %d: ", i);
            if (ok) {
                pw.print(curDist + " ");
                if (start == next && set.size() == n) {
                    if (curDist < min) {
                        min = curDist;
                        minI = i;
                    }
                    if (simple) {
                        pw.print("(TS simple cycle)");
                    } else {
                        pw.print("(TS cycle)");
                    }
                } else {
                    pw.print("(Not a TS cycle)");
                }
            } else {
                pw.print("NA (Not a TS cycle)");
            }
            pw.println();
        }
        pw.format("Shortest Dist(%d) = %d", minI, min);
    }

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new OutputStreamWriter(System.out));
        st = new StreamTokenizer(br);

        solve();
        pw.flush();
    }

    static String next(int len) throws IOException {
        int b = br.read();
        while (b == '\n' || b == ' ' || b == '\r' || b == '\t') {
            b = br.read();
        }
        char ch[] = new char[len];
        ch[0] = (char) b;
        int idx = 1;
        while (idx < len && (b = br.read()) != ' ' && b != '\n' && b != '\r' && b != '\t') {
            ch[idx++] = (char) b;
        }
        return String.valueOf(ch).trim();
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextLine() throws IOException {
        return br.readLine();
    }

    static double nextDouble() throws IOException {
        st.nextToken();
        return st.nval;
    }
}
