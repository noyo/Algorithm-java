package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/4 23:35
 * @see eight
 */
public class Uva11093 {



    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int k = 1; k <= T; k++) {
            int n = sc.nextInt();
            int s[] = new int[n];
            int left = 0;
            int right = 0;
            int cur = 0;
            for (int i = 0; i < n; i++) {
                s[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                right += s[i] - sc.nextInt();
                if (right < 0) {
                    left -= right;
                    right = 0;
                    cur = (i + 1) % n;
                }
            }
            System.out.println("Case " + k +  ": " + (right >= left ? "Possible from station " + (cur + 1) : "Not possible"));
        }
    }
}
