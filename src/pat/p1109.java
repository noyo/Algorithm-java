package pat;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/11/20 16:11
 * @see pat
 */
public class p1109 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static void solve() throws IOException {
        int n = nextInt();
        int k = nextInt();
        int per = n / k;
        List<String> names = new ArrayList<>();
        List<Integer> hgt = new ArrayList<>();
        Integer order[] = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
            names.add(next(8));
            hgt.add(nextInt());
        }
        Arrays.sort(order, (o1, o2) -> (hgt.get(o1).equals(hgt.get(o2))) ? names.get(o2).compareTo(names.get(o1))
                : hgt.get(o1) - hgt.get(o2));
        int first = n - (per * (k - 1));
        int idx = n - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.delete(0, sb.length());
            sb.append(names.get(order[idx--]));
            int end = (i == 0 ? first : per);
            for (int j = 1; j < end; j++) {
                sb.insert(0, names.get(order[idx--]) + " ");
                j++;
                if (j >= end) {
                    break;
                }
                sb.append(" ").append(names.get(order[idx--]));
            }
            pw.println(sb.toString());
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
