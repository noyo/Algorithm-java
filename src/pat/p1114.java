package pat;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1114 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int getP(int p[], int x) {
        if (p[x] == -1) {
            return x;
        }
        return p[x] = getP(p, p[x]);
    }

    static void solve() throws IOException {
        int n = nextInt();
        int p[] = new int[10000];
        Arrays.fill(p, -1);
        Set<Integer> vis = new HashSet<>();
        double top[][] = new double[10000][3];
        for (int i = 0; i < 10000; i++) {
            top[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            List<Integer> data = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int a = nextInt();
                if (a != -1) {
                    data.add(a);
                }
            }
            int k = nextInt();
            for (int j = 0; j < k; j++) {
                data.add(nextInt());
            }
            vis.addAll(data);
            data.sort(Comparator.naturalOrder());
            int root = 0;
            int pa = getP(p, data.get(root));
            for (int j = root + 1; j < data.size(); j++) {
                int curP = getP(p, data.get(j));
                if (curP < pa) {
                    curP ^= pa;
                    pa ^= curP;
                    curP ^= pa;
                }
                if (curP != pa) {
                    p[curP] = pa;
                    top[pa][0] += Math.max(1, top[curP][0]);
                    top[pa][1] += top[curP][1];
                    top[pa][2] += top[curP][2];
                }
            }
            top[pa][1] += nextInt();
            top[pa][2] += nextInt();
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            if (vis.contains(i) && (p[i] == -1)) {
                ans.add(i);
            }
        }
        for (int i = 0; i < ans.size(); i++) {
            top[ans.get(i)][1] /= top[ans.get(i)][0];
            top[ans.get(i)][2] /= top[ans.get(i)][0];
        }
        ans.sort((o1, o2) -> {
            if (top[o1][2] == top[o2][2]) {
                return o1 - o2;
            }
            return top[o2][2] > top[o1][2] ? 1 : -1;
        });
        pw.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            int k = ans.get(i);
            pw.format("%04d %d %.3f %.3f\n", k, (int) top[k][0], top[k][1], top[k][2]);
        }
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
