package meituan.n2017;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/6 14:22
 * @see meituan.n2017
 */
public class a1 {

    private static class Cup {
        long t;
        long c;

        Cup(long t, long c) {
            this.t = t;
            this.c = c;
        }

        @Override
        public String toString() {
            return  t + " " + c;
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long T = sc.nextLong();
        long C = sc.nextLong();
        List<Cup> cups = new ArrayList<>();
        long minT = Integer.MAX_VALUE;
        long maxT = 0;
        long V = C;
        long total = 0;
        boolean p = true;

        for (int i = 0; i < n; i++) {
            long t = sc.nextLong();
            long c = sc.nextLong();
            minT = Math.min(minT, t);
            maxT = Math.max(maxT, t);
            cups.add(new Cup(t, c));
            V += cups.get(cups.size() - 1).c;
            total += t * c;
            if (p) {
                p = T == t;
            }
        }
        if (p) {
            System.out.println("Possible");
            System.out.format("%.4f", (double) T);
            return;
        }

        long average;
        if (minT > T) {
            average = minT;
        } else if (maxT < T) {
            average = maxT;
        } else {
            System.out.println("Impossible");
            return;
        }

        long flag = total + (T * C) - average * V;

        if (average > T && flag > 0 || average < T && flag < 0) {
            System.out.println("Impossible");
            return;
        }
        System.out.println("Possible");
        if (average < T) {
            System.out.format("%.4f", (total + (T * C)) / (double)  V);
        } else {
            System.out.format("%.4f", (double) average);
        }
    }
}
