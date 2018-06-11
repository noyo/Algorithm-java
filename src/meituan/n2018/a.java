package meituan.n2018;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/9 18:55
 * @see meituan.n2018
 */
public class a {
    
    private static Map<Character, Integer> row = new HashMap<>();
    private static Map<Character, Integer> col = new HashMap<>();
    
    static {
        row.put('@', 0);
        row.put('!', 0);
        row.put(':', 0);
        row.put('A', 0);
        row.put('B', 0);
        row.put('C', 0);
        row.put('D', 0);
        row.put('E', 0);
        row.put('F', 0);
        row.put('G', 1);
        row.put('H', 1);
        row.put('I', 1);
        row.put('J', 1);
        row.put('K', 1);
        row.put('L', 1);
        row.put('M', 1);
        row.put('N', 1);
        row.put('O', 1);
        row.put('P', 2);
        row.put('Q', 2);
        row.put('R', 2);
        row.put('S', 2);
        row.put('T', 2);
        row.put('U', 2);
        row.put('V', 2);
        row.put('W', 2);
        row.put('X', 2);
        row.put('Y', 2);
        row.put('Z', 2);
        col.put('@', 0);
        col.put('!', 0);
        col.put(':', 0);
        col.put('A', 1);
        col.put('B', 1);
        col.put('C', 1);
        col.put('D', 2);
        col.put('E', 2);
        col.put('F', 2);
        col.put('G', 0);
        col.put('H', 0);
        col.put('I', 0);
        col.put('J', 1);
        col.put('K', 1);
        col.put('L', 1);
        col.put('M', 2);
        col.put('N', 2);
        col.put('O', 2);
        col.put('P', 0);
        col.put('Q', 0);
        col.put('R', 0);
        col.put('S', 0);
        col.put('T', 1);
        col.put('U', 1);
        col.put('V', 1);
        col.put('W', 2);
        col.put('X', 2);
        col.put('Y', 2);
        col.put('Z', 2);
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            char ch[] = sc.next().toCharArray();
            int r = 0;
            int c = 0;
            int sum = 0;
            for (int i = 0; i < ch.length; i++) {
                int nextR = row.get(ch[i]);
                int nextC = col.get(ch[i]);
                sum += Math.abs(nextR - r) + Math.abs(nextC - c);
                r = nextR;
                c = nextC;
            }
            System.out.println(sum);
        }
    }
}
