package pat;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1096 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = nextInt();
        int sqrt = (int) Math.sqrt(n);
        List<Integer> fac = new ArrayList<>();
        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                fac.add(i);
                if (n / i != i) {
                    fac.add(n / i);
                }
            }
        }
        fac.sort(Comparator.naturalOrder());
        int max = 0;
        int maxI = 0;
        for (int i = 1; i < fac.size(); i++) {
            int cur = n / fac.get(i);
            int cnt = 1;
            for (int j = i + 1; j < fac.size(); j++) {
                if (fac.get(j) == fac.get(j - 1) + 1 && cur % fac.get(j) == 0) {
                    cur /= fac.get(j);
                    cnt++;
                } else {
                    break;
                }
            }
            if (cnt > max) {
                max = cnt;
                maxI = i;
            }
        }
        pw.println(max);
        for (int i = maxI; i < maxI + max; i++) {
            pw.print(fac.get(i));
            if (i < maxI + max - 1) {
                pw.print("*");
            }
        }
        pw.println();
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
