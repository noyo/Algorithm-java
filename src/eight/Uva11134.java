package eight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: eight
 * author: Chris
 * date: 2018/5/24 17:28
 */
public class Uva11134 {
    static class Rook {
        int lx;
        int ly;
        int rx;
        int ry;
        boolean used = false;

        Rook(int x1, int y1, int x2, int y2) {
            lx = x1;
            ly = y1;
            rx = x2;
            ry = y2;
        }

        boolean place(int x, int y) {
            return !used && x >= lx && x <= rx && y >= ly && y <= ry;
        }

        @Override
        public String toString() {
            return lx + " " + ly + " " + rx + " " + ry;
        }
    }

    static class Point {
        int x;
        int y;
        int id;
        Point(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    private static boolean dfs(Rook[] rooks, boolean[] visit, int n, List<Point> list) {
        if (list.size() == n) {
            list.sort(Comparator.comparingInt(o -> o.id));
            for (Point p : list) {
                System.out.println(p);
            }
            return true;
        }

        for (int i = 1; i <= n; i++) {
            if (visit[i]) {
                continue;
            }
            for (int j = 1; j <= n; j++) {
                if (rooks[j].place(list.size() + 1, i)) {
                    visit[i] = true;
                    list.add(new Point(list.size() + 1, i, j));
                    rooks[j].used = true;
                    if (dfs(rooks, visit, n, list)) {
                        return true;
                    }
                    rooks[j].used = false;
                    list.remove(list.size() - 1);
                    visit[i] = false;
                }
            }
        }

        return false;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = Integer.parseInt(sc.nextLine());
            if (n == 0) {
                break;
            }
            boolean visit[] = new boolean[n + 1];
            Rook[] rooks = new Rook[n + 1];
            for (int i = 1; i <= n; i++) {
                rooks[i] = new Rook(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                sc.nextLine();
            }

            if (!dfs(rooks, visit, n, new ArrayList<>())) {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
