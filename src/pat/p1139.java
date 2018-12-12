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
public class p1139 {

    static BufferedReader br;
    static PrintWriter pw;
    static StreamTokenizer st;

    static int n, m, k;
    static Map<String, Set<String>> map = new HashMap<>();
    static Map<String, Set<String>> spMap = new HashMap<>();

    static void solve() throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 0; i < m; i++) {
            String a = next(5);
            String b = next(5);
            boolean eqaul = a.length() == b.length();
            a = a.charAt(0) == '-' ? a.substring(1) : a;
            b = b.charAt(0) == '-' ? b.substring(1) : b;
            if (!eqaul) {
                map.putIfAbsent(a, new HashSet<>());
                map.putIfAbsent(b, new HashSet<>());
                map.get(a).add(b);
                map.get(b).add(a);
            } else {
                spMap.putIfAbsent(a, new HashSet<>());
                spMap.putIfAbsent(b, new HashSet<>());
                spMap.get(a).add(b);
                spMap.get(b).add(a);
            }
        }
        k = nextInt();
        while (k-- > 0) {
            String a = next(5);
            String b = next(5);
            boolean equal = a.length() == b.length();
            a = a.charAt(0) == '-' ? a.substring(1) : a;
            b = b.charAt(0) == '-' ? b.substring(1) : b;
            Set<String> s1 = spMap.get(a);
            Set<String> s2 = spMap.get(b);
            if (null == s1 || null == s2 || 0 == s1.size() || 0 == s2.size()) {
                pw.format("0\n");
                continue;
            }
            int cnt = 0;
            List<String> ans = new ArrayList<>();
            for (String s : s1) {
                Set<String> k_v = equal ? spMap.get(s) : map.get(s);
                if (s.equals(b) || null == k_v || k_v.size() == 0) {
                    continue;
                }
                for (String ss : s2) {
                    if (!ss.equals(a) && k_v.contains(ss)) {
                        ans.add(s + " " + ss);
                        cnt++;
                    }
                }
            }
            pw.format("%d\n", cnt);
            ans.sort(Comparator.naturalOrder());
            for (String s : ans) {
                pw.format("%s\n", s);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("src/in.txt"));
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
