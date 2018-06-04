package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/4 18:03
 * @see eight
 */
public class Uva10954 {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            Arrays.sort(a);
            int i = 0;
            int sum = 0;
            while (i < n - 1) {
                a[i + 1] += a[i];
                sum += a[i + 1];
                i++;
                Arrays.sort(a, i, n);
            }
            System.out.println(sum);
        }
    }
}
