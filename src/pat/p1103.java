package pat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1103 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int max = 0;
    static List<Integer> ans = new ArrayList<>();

    static boolean dfs(int n, int k, int p, int up, int sum, List<Integer> res) {
        if (n < 0) {
            return false;
        }
        if (k == 0) {
            if (n != 0) {
                return false;
            }
            if (sum > max) {
                max = sum;
                ans.clear();;
                ans.addAll(res);
            }
            return true;
        }
        boolean ok = false;
        for (int i = up; i >= 1; i--) {
            res.add(i);
            ok |= dfs((int) (n - Math.pow(i, p)), k - 1, p, i, sum + i, res);
            res.remove(res.size() - 1);
        }
        return ok;
    }

    static void solve() throws IOException {
        int n = nextInt();
        int k = nextInt();
        int p = nextInt();
        int max = 1;
        while (Math.pow(max + 1, p) <= n - k + 1) {
            max++;
        }
        boolean ok = dfs(n, k, p, max, 0, new ArrayList<>());

        if (!ok) {
            pw.println("Impossible");
        } else {
            pw.print(n + " = ");
            for (int i = 0; i < ans.size(); i++) {
                pw.print(ans.get(i) + "^" + p);
                if (i < ans.size() - 1) {
                    pw.print(" + ");
                }
            }
            pw.println();
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
