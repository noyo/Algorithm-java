package meituan.n2018.Bturn;

import java.util.*;

public class A {

    private static class Zoom implements Comparable{
        int r;
        int c;
        int z;
        int t;

        static int H = 60 * 60 * 1000;
        static int M = 60 * 1000;

        Zoom(int r, int c, int z, String time) {
            this.r = r;
            this.c = c;
            this.z = z;
            String ss[] = time.split(":");
            t = Integer.parseInt(ss[0]) * H + Integer.parseInt(ss[1]) * M + (int) (Float.parseFloat(ss[2]) * 1000);
        }

        @Override
        public int compareTo(Object o) {
            Zoom z = (Zoom) o;
            return t - z.t;
        }
    }

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] light = new int[n + 1][m + 1];

        PriorityQueue<Zoom> queue = new PriorityQueue<>();
        List<Zoom> zooms = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            queue.offer(new Zoom(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.next()));
        }
        while (!queue.isEmpty()) {
            zooms.add(queue.poll());
        }

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
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        solve(sc);
    }
}
