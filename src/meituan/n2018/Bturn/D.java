package meituan.n2018.Bturn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/23 11:56
 * @see meituan.n2018.Bturn
 */
public class D {
    static double ti = 0.000000001;

    private static double dist(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    static double angle(double a, double b, double c) {
        double t = (a * a + b * b - c * c) / (2 * a * b);
        return Math.acos(t);
    }

    static double dist2(double a, double b, double t) {
        return Math.sqrt(a * a + b * b - 2 * a * b * Math.cos(t));
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        int r = 0;
        double res = 0, x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        r = sc.nextInt();
        x1 = sc.nextDouble();
        y1 = sc.nextDouble();
        x2 = sc.nextDouble();
        y2 = sc.nextDouble();

        double d = dist(x1, x2, y1, y2);
        double a = dist(x1, 0, y1, 0);
        double b = dist(x2, 0, y2, 0);
        double x3 = r / a * x1, y3 = r / a * y1;
        double x4 = r / b * x2, y4 = r / b * y2;
        double x5 = -x3, y5 = -y3;
        double x6 = -x4, y6 = -y4;
        double t0 = angle(r, r, dist(x3, x6, y3, y6));
        double s = 0, e = t0;
        double d1 = r - a + dist(x3, x6, y3, y6);
        double d2 = r - b + dist(x4, x5, y4, y5);
        double rs = 1000000000;

        while (s <= e) {
            double m = (s + e) / 2, m1 = m + ti, m2 = m - ti;
            double x = dist2(a, r, m), xs = dist2(a, r, m1), xe = dist2(a, r, m2);
            double y = dist2(b, r, t0 - m), ys = dist2(b, r, t0 - m1), ye = dist2(b, r, t0 - m2);
            double dt0 = x + y, dt1 = xs + ys, dt2 = xe + ye;
            if (dt0 <= dt1 && dt0 <= dt2) {
                rs = dt0;
                break;
            } else if (Math.abs(dt1 - dt0) <= ti && Math.abs(dt2 - dt0) <= ti) {
                rs = dt0;
                break;
            } else if (dt1 < dt0) {
                s = m;
            } else {
                e = m;
            }
        }
        System.out.format("%.9f", Math.min(d, rs));
    }
}
