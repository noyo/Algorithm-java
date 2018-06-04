package eight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: eight
 * author: Chris
 * date: 2018/5/25 17:28
 */
public class Uva1606 {
    private static class Point {
        int x;
        int y;
        int type;//0-water 1-acetone
        Point(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + type;
        }
    }

    private static void sort(List<Point> list, Point p){
        list.sort((o1, o2) -> {
            int dx1 = o1.x - p.x;
            int dy1 = o1.y - p.y;
            int dx2 = o2.x - p.x;
            int dy2 = o2.y - p.y;

            if (dy1 == 0 && dy2 != 0 || dy1 != 0 && dy2 == 0) {
//                return dy1 == 0 ? (dx1 > 0 ? -1 : 1) : (dx2 > 0 ? 1 : -1);
                return dy1 == 0 ? -1 : 1;
            }
            if (dx1 == 0 && dx2 != 0 || dx2 == 0 && dx1 != 0) {
                return dx1 == 0 ? (dy2 >= 0 ? dx2 : -dx2) : (dy1 >= 0 ? -dx1 : dx1);
            }
            if (dx1 * dy1 > 0 && dx2 * dy2 < 0) {
                return -1;
            }
            if (dx1 * dy1 < 0 && dx2 * dy2 > 0) {
                return 1;
            }
            return (dy1 * dy2 > 0 ? 1 : -1) * (dy1 * dx2 - dy2 * dx1);
        });
    }

    private static void input(List<Point> data, Scanner sc, int n) {
        for (int i = 0; i < n; i++) {
            data.add(new Point(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            sc.nextLine();
        }
    }

    private static boolean equalSlope(Point p1, Point p2, Point base) {
        int dx1 = p1.x - base.x;
        int dy1 = p1.y - base.y;
        int dx2 = p2.x - base.x;
        int dy2 = p2.y - base.y;
        return dy2 * dx1 == dy1 * dx2;
    }

    private static int getMax(int n, List<Point> data) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            Point cur = data.get(i);
            List<Point> copy = new ArrayList<>(data);
            copy.remove(i);
            sort(copy, cur);
            int lastLinePos[] = new int[2];
            int lastLineNeg[] = new int[2];
            int left[] = new int[2];
            int right[] = new int[2];
            int m = n - 1;
            for (int j = 0; j < (m << 1);) {
                for (int k = 0; k < 2; k++) {
                    left[k] -= lastLinePos[k];
                    right[k] -= lastLineNeg[k];
                }
                if (j == m) {
                    int tmp[] = right;
                    right = left;
                    left = tmp;
                }
                int curPos[] = new int[2];
                int curNeg[] = new int[2];
                do {
                    int type = copy.get(j % m).type;
                    if (copy.get(j % m).y > cur.y || (copy.get(j % m).y == cur.y && copy.get(j % m).x > cur.x)) {
                        curPos[type]++;
                        if (j < m) left[type]++;
                        right[type]++;
                    } else {
                        curNeg[type]++;
                        left[type]++;
                        if (j < m) right[type]++;
                    }
                    j++;
                } while ((j < (m << 1) && j != m) && equalSlope(copy.get(j % m), copy.get((j % m) - 1), cur));
                max = Math.max(max, Math.max(left[0] + right[1], left[1] + right[0]));
                if (max == m) {
                    return n;
                }
                lastLinePos = curPos;
                lastLineNeg = curNeg;
            }
        }
        return max + 1;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = Integer.parseInt(sc.nextLine());
            if (n == 0) {
                break;
            }
            List<Point> data = new ArrayList<>();
            input(data, sc, n);
            System.out.println(getMax(n, data));
        }
    }
}
