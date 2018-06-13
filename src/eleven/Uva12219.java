package eleven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: eleven
 * author: Chris
 * date: 2018/6/13 14:52
 */
public class Uva12219 {

    private static int cnt;

    private static String dfs(String s, Map<String, Integer> map) {

        int start = s.indexOf(',');
        if (start == -1) {
            return s;
        }

        start = s.indexOf('(') + 1;
        StringBuilder sb = new StringBuilder(s.substring(0, start));

        int left = 0;
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                left--;
            }
            if (i == s.length() - 2 || c == ',' && left == 0) {
                String sub = s.substring(start, i == s.length() - 2 ? i + 1 : i);
                if (map.containsKey(sub)) {
                    sb.append(map.get(sub));
                } else {
                    map.put(sub, cnt++);
                    sb.append(dfs(sub, map));
                }
                if (i < s.length() - 2) {
                    sb.append(',');
                    start = i + 1;
                }
            }
        }
        sb.append(')');

        return sb.toString();
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            String s = sc.next();
            cnt = 2;
            Map<String, Integer> map = new HashMap<>();

            System.out.println(dfs(s, map));
        }
    }
}
