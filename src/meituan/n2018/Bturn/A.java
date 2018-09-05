package meituan.n2018.Bturn;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class A {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;


    private static class Zoom implements Comparable{
        int r;
        int c;
        int z;
        int t;

        static int H = 60 * 60 * 1000;
        static int M = 60 * 1000;

        Zoom(int r, int c, int z, String[] ss) {
            this.r = r;
            this.c = c;
            this.z = z;
            t = Integer.parseInt(ss[0]) * H + Integer.parseInt(ss[1]) * M + (int) (Float.parseFloat(ss[2]) * 1000);
        }

        @Override
        public int compareTo(Object o) {
            Zoom z = (Zoom) o;
            return t - z.t;
        }
    }

    private static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();
        int[][] light = new int[n + 1][m + 1];

//        PriorityQueue<Zoom> queue = new PriorityQueue<>();
        List<Zoom> zooms = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            int r = nextInt();
            int c = nextInt();
            int z = nextInt();
            zooms.add(new Zoom(r, c, z, nextSS(":")));
//            queue.offer(new Zoom(r, c, z, nextSS(":")));
        }
        zooms.sort(Comparator.comparingInt(o -> o.t));
//        while (!queue.isEmpty()) {
//            zooms.add(queue.poll());
//        }

        int max = 0;
        int cnt = 0;
        int maxI = 0;

        for (int i = 0; i < k; i++) {
            Zoom zoom = zooms.get(i);
            int r = zoom.r;
            int c = zoom.c;
            if (zoom.z == 0) {
                if (light[r][c] == 0) {
                    cnt++;
                    if (cnt >= max) {
                        max = cnt;
                        maxI = i;
                    }
                }
                light[r][c]++;
            } else {
                if (light[r][c] == 1) {
                    cnt--;
                }
                light[r][c]--;
            }
        }

        for (int i = k - 1; i > maxI; i--) {
            Zoom zoom = zooms.get(i);
            int r = zoom.r;
            int c = zoom.c;
            if (zoom.z == 1) {
                light[r][c]++;
            } else {
                light[r][c]--;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (light[i][j] > 0) {
//                    System.out.print(1);
                    pw.print(1);
                } else {
//                    System.out.print(0);
                    pw.print(0);
                }
            }
//            System.out.println();
            pw.println();
        }
    }

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StreamTokenizer(br);
        pw = new PrintWriter(new OutputStreamWriter(System.out));
        st.ordinaryChar('\''); //指定单引号、双引号和注释符号是普通字符
        st.ordinaryChar('\"');
        st.ordinaryChar('/');

        long t = System.currentTimeMillis();
        solve();
        pw.flush();
    }

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static long nextLong() throws IOException {
        st.nextToken();
        return (long) st.nval;
    }

    private static double nextDouble() throws IOException {
        st.nextToken();
        return st.nval;
    }

    private static String[] nextSS(String reg) throws IOException {
        return br.readLine().split(reg);
    }

    private static String nextLine() throws IOException {
        return br.readLine();
    }
}
