package ten;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: ten
 * author: Chris
 * date: 2018/6/12 14:43
 */
public class Uva12169 {

    private static int MAX = 10001;

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int t[] = new int[T << 1 | 1];
        for (int i = 1; i < T << 1; i += 2) {
            t[i] = sc.nextInt();
        }
        if (T <= 1) {
            System.out.println(T == 0 ? 0 : t[0]);
            return;
        }

        for (int a = 0; a < MAX; a++) {
            for (int b = 0; b < MAX; b++) {
                boolean flag = true;
                for (int i = 2; i < (T << 1 | 1); i++) {
                    int cur = (a * t[i - 1] + b) % MAX;
                    if (i % 2 == 0) {
                        t[i] = cur;
                    } else if (t[i] != cur) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int i = 2; i < (T << 1 | 1); i += 2) {
                        System.out.println(t[i]);
                    }
                    return;
                }
            }
        }
    }
}
