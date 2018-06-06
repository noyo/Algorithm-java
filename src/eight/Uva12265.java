package eight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/5 22:02
 * @see eight
 */
public class Uva12265 {

    private static class Rect {
        int w;
        int h;

        Rect(int w, int h) {
            this.w = w;
            this.h = h;
        }

        int getHPrimeter() {
            return w + h;
        }

        @Override
        public String toString() {
            return w + " " + h;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Map<Integer, Integer> cnt = new TreeMap<>();

            for (int i = 0; i < n; i++) {
                char ch[] = sc.next().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (ch[j] == '#') {
                        continue;
                    }

//                    cnt.put(max, cnt.getOrDefault(max, 0) + 1);
                }
            }
            if (cnt.size() == 0) {
                System.out.println(0);
            }
            for (int key : cnt.keySet()) {
                System.out.println(cnt.get(key) + " x " + key * 2);
            }
        }
    }
}
