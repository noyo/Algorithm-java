package pat;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1100 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        Map<Integer, String> mar1 = new HashMap<>();
        Map<Integer, String> mar2 = new HashMap<>();
        Map<String, Integer> earth1 = new HashMap<>();
        Map<String, Integer> earth2 = new HashMap<>();
        String ss[] = "jan, feb, mar, apr, may, jun, jly, aug, sep, oct, nov, dec".split(", ");
        for (int i = 1; i <= ss.length; i++) {
            mar1.put(i, ss[i - 1]);
            earth1.put(ss[i - 1], i);
        }
        ss = "tam, hel, maa, huh, tou, kes, hei, elo, syy, lok, mer, jou".split(", ");
        for (int i = 1; i <= ss.length; i++) {
            mar2.put(i, ss[i - 1]);
            earth2.put(ss[i - 1], i);
        }
        int n = Integer.parseInt(nextLine());
        for (int i = 0; i < n; i++) {
            String s = nextLine();
            try {
                int k = Integer.parseInt(s);
                if (k == 0) {
                    pw.println("tret");
                    continue;
                }
                if (k > 12) {
                    pw.print(mar2.get(k / 13));
                }
                if (k > 12 && k % 13 != 0) {
                    pw.print(" ");
                }
                if (k % 13 != 0) {
                    pw.print(mar1.get(k % 13));
                }
                pw.println();
            } catch (Exception e) {
                if (s.equals("tret")) {
                    pw.println(0);
                    continue;
                }
                ss = s.split(" ");
                int ans = 0;
                if (earth2.containsKey(ss[0])) {
                    ans += 13 * earth2.get(ss[0]);
                }
                if (earth1.containsKey(ss[ss.length - 1])) {
                    ans += earth1.get(ss[ss.length - 1]);
                }
                pw.println(ans);
            }
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
