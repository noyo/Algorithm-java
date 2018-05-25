package eight;

import java.util.ArrayList;
import java.util.Collections;
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
    }

    static void sort(List<Point> list, Point p){
        Collections.sort(list, (o1, o2) -> {
            int dx1 = o1.x - p.x;
            int dy1 = o1.y - p.y;
            int dx2 = o2.x - p.x;
            int dy2 = o2.y - p.y;
            if (dx1 * dy1 > 0 && dx2 * dy2 < 0) {
                return -1;
            }
            if (dx1 * dy1 < 0 && dx2 * dy2 > 0) {
                return 1;
            }
            return dy1 * dx2 - dy2 * dx1;
        });
    }

    static void input(List<Point> data, Scanner sc, int n) {
        for (int i = 0; i < n; i++) {
            data.add(new Point(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            sc.nextLine();
        }
    }

    static boolean equalSlope(Point p1, Point p2, Point base) {
        int dx1 = p1.x - base.x;
        int dy1 = p1.y - base.y;
        int dx2 = p2.x - base.x;
        int dy2 = p2.y - base.y;
        return dy2 * dx1 == dy1 * dx2;
    }

    private static void getMax(int n, List<Point> data) {
        int left[] = new int[2];
        int right[] = new int[2];
        for (int i = 0; i < n; i++) {
            Point cur = data.get(i);
            List<Point> copy = new ArrayList<>(data);
            copy.remove(i);
            sort(copy, cur);
            int lastLinePos[] = new int[2];
            int lastLineNeg[] = new int[2];
            for (int j = 0; j < n - 1 && copy.get(j).y >= cur.y;) {
                for (int k = 0; k < 2; k++) {
                    left[k] -= lastLinePos[k];
                    right[k] -= lastLineNeg[k];
                }
                int curPos[] = new int[2];
                int curNeg[] = new int[2];
                do {
                    int type = copy.get(j).type;
                    if (copy.get(j).y > cur.y) {
                        curPos[type]++;
                    } else {
                        curNeg[type]++;
                    }
                    left[type]++;
                    right[type]++;
                    j++;
                } while (j < n - 1 && copy.get(j).y >= cur.y && equalSlope(copy.get(j), copy.get(j - 1), cur));
                lastLinePos = curPos;
                lastLineNeg = curNeg;
            }
        }
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
            getMax(n, data);
        }
    }
}
