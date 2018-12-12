package pat;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1137 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int p, m, n;
    static Map<String, Integer> mapIdx = new HashMap<>();
    static Map<Integer, String> name = new HashMap<>();
    static int GP[], GM[], GF[], G[];

    static void solve() throws IOException {
        p = nextInt();
        m = nextInt();
        n = nextInt();
        String[] ss;
        int idx = 0;
        GP = new int[10001];
        GM = new int[10001];
        GF = new int[10001];
        G = new int[10001];
        Arrays.fill(GP, -1);
        Arrays.fill(GM, -1);
        Arrays.fill(GF, -1);
        Arrays.fill(G, -1);
        for (int i = 0; i < p; i++) {
            ss = nextLine().split(" ");
            if (!mapIdx.containsKey(ss[0])) {
                name.put(idx, ss[0]);
                mapIdx.put(ss[0], idx++);
            }
            GP[mapIdx.get(ss[0])] = Integer.parseInt(ss[1]);
        }
        for (int i = 0; i < m; i++) {
            ss = nextLine().split(" ");
            if (!mapIdx.containsKey(ss[0])) {
                name.put(idx, ss[0]);
                mapIdx.put(ss[0], idx++);
            }
            GM[mapIdx.get(ss[0])] = Integer.parseInt(ss[1]);
        }
        for (int i = 0; i < n; i++) {
            ss = nextLine().split(" ");
            if (!mapIdx.containsKey(ss[0])) {
                name.put(idx, ss[0]);
                mapIdx.put(ss[0], idx++);
            }
            GF[mapIdx.get(ss[0])] = Integer.parseInt(ss[1]);
        }
        for (int i = 0; i < idx; i++) {
            if (GM[i] > GF[i]) {
                G[i] = (int) (GM[i] * 0.4 + GF[i] * 0.6 + 0.5);
            } else {
                G[i] = GF[i];
            }
        }
        Integer order[] = new Integer[idx];
        for (int i = 0; i < idx; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (o1, o2) -> G[o1] == G[o2] ? name.get(o1).compareTo(name.get(o2)): -G[o1] + G[o2]);
        for (int i = 0; i < n; i++) {
            int k = order[i];
            if (GP[k] < 200 || G[k] < 60) {
                continue;
            }
            pw.format("%s %d %d %d %d\n", name.get(k), GP[k], GM[k], GF[k], G[k]);
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
