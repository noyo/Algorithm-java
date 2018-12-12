package pat;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1098 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n;
    static int a[];
    static int b[];

    static void solve() throws IOException {
        n = nextInt();
        a = new int[n + 1];
        b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
        }
        boolean ok = true;
        int i = 1;
        for (; i <= n ; i++) {
            b[i] = nextInt();
        }
        for (i = 2; i <= n; i++) {
            if (b[i] < b[i - 1]) {
                break;
            }
        }
        for (int j = i; j <= n; j++) {
            if (b[j] != a[j]) {
                ok = false;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (ok) {
            pw.format("Insertion Sort\n");
            for (int j = 1; j < i; j++) {
                if (ok && b[j] > b[i]) {
                    sb.append(b[i]).append(" ");
                    ok = false;
                }
                sb.append(b[j]).append(" ");
            }
            for (int j = i + 1; j <= n; j++) {
                sb.append(b[j]).append(" ");
            }
        } else {
            pw.format("Heap Sort\n");
            int j = n;
            while (b[1] <= b[j]) {
                j--;
            }
            b[1] ^= b[j];
            b[j] ^= b[1];
            b[1] ^= b[j];
            i = 1;
            while (i < j) {
                int L = (i << 1) <= j ? b[i << 1] : 0;
                int R = (i << 1 | 1) <= j ? b[i << 1 | 1] : 0;
                if (L <= b[i] && R <= b[i] || (i << 1) >= j) {
                    break;
                }
                if (L >= R || L > b[i] && (i << 1 | 1) >= j) {
                    b[i << 1] = b[i];
                    b[i] = L;
                    i = i << 1;
                } else if ((i << 1 | 1) < j){
                    b[i << 1 | 1] = b[i];
                    b[i] = R;
                    i = i << 1 | 1;
                } else {
                    break;
                }
            }
            for (int k = 1; k <= j; k++) {
                if (b[k] != 0) {
                    sb.append(b[k]).append(" ");
                }
            }
            for (j = j + 1; j <= n; j++) {
                sb.append(b[j]).append(" ");
            }
        }
        pw.format("%s", sb.toString().trim());
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
