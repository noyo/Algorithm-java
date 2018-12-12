package pat;

import java.io.*;
import java.util.Arrays;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1078 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static boolean prime(int num) {
        int sqrt = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void solve() throws IOException {
        int MAX = nextInt();
        int n = nextInt();
        if (MAX <= 2) {
            MAX = 2;
        } else if (MAX % 2 == 0) {
            MAX++;
        }
        if (MAX > 2) {
            while (!prime(MAX)) {
                MAX += 2;
            }
        }
        int table[] = new int[MAX];
        Arrays.fill(table, -1);
        for (int i = 1; i <= n; i++) {
            int val = nextInt();
            int idx = val % MAX;
            boolean ok = false;
            for (int j = 0; j < MAX; j++) {
                idx = (val + j * j) % MAX;
                if (table[idx] == -1) {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                table[idx] = val;
                pw.format("%d", idx);
            } else {
                pw.print("-");
            }
            if (i < n) {
                pw.format(" ");
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
