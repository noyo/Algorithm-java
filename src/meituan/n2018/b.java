package meituan.n2018;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/9 18:55
 * @see meituan.n2018
 */
public class b {

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long a[] = new long[n];
        long b[] = new long[n];
        int preA[] = new int[n + 1];
        int preB[] = new int[n + 1];

        int pre = -1;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
            if (a[i] > 0) {
                preA[i] = pre;
                pre = i;
            }
        }
        preA[n] = pre;
        pre = -1;
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextLong();
            if (b[i] > 0) {
                preB[i] = pre;
                pre = i;
            }
        }
        preB[n] = pre;

        long sum = 0;
        int curA = preA[n];
        int curB = preB[n];
        while (curA >= 0 && curB >= 0) {
            while (curA >= 0 && a[curA] == 0) {
                curA = preA[curA];
            }
            while (curB >= 0 && curA < curB) {
                curB = preB[curB];
            }
            if (curB < 0) {
                break;
            }
            if (a[curA] >= b[curB]) {
                sum += (curA - curB) * b[curB];
                a[curA] -= b[curB];
                b[curB] = 0;
                curB = preB[curB];
            } else {
                sum += (curA - curB) * a[curA];
                b[curB] -= a[curA];
                a[curA] = 0;
                curA = preA[curA];
            }
        }

        curA = preA[n];
        while (curA >= 0) {
            sum += a[curA] * curA;
            curA = preA[curA];
        }
        curB= preB[n];
        while (curB >= 0) {
            sum += b[curB] * curB;
            curB = preB[curB];
        }
        System.out.println(sum);
    }
}
