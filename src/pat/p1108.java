package pat;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1108 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = Integer.parseInt(nextLine());
        String ss[] = nextLine().split(" ");
        double sum = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            double cur;
            try {
                cur = Double.parseDouble(ss[i]);
                if (cur > 1000 || cur < -1000) {
                    throw new Exception();
                }
                int idx = ss[i].lastIndexOf('.');
                if (idx != -1 && idx < ss[i].length() - 3) {
                    throw new Exception();
                }
                sum += cur;
                cnt++;
            } catch (Exception ignored) {
                pw.format("ERROR: %s is not a legal number\n", ss[i]);
            }
        }
        if (cnt == 0) {
            pw.println("The average of 0 numbers is Undefined");
        } else {
            pw.format("The average of %d %s is %.2f\n", cnt, cnt == 1 ? "number" : "numbers", sum / cnt);
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
