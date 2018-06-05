package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/5 18:25
 * @see eight
 */
public class Uva1442 {

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int Z = sc.nextInt();
        while (Z-- > 0) {
            int n = sc.nextInt();
            int bot[] = new int[n];
            int top[] = new int[n];
            int volume = 0;

            for (int i = 0; i < n; i++) {
                bot[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                top[i] = sc.nextInt();
            }

            int h[] = new int[n];
            int level = top[0];
            for (int i = 0; i < n; i++) {
                 level = Math.max(level, bot[i]);
                 level = Math.min(level, top[i]);
                 h[i] = level;
            }
            level = top[n - 1];
            for (int i = n - 1; i >= 0; i--) {
                level = Math.max(level, bot[i]);
                level = Math.min(level, top[i]);
                volume += Math.min(level, h[i]) - bot[i];
            }
            System.out.println(volume);
        }
    }
}
