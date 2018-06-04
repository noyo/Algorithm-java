package six.practices;

import util.IOUtils;

import java.util.List;
import java.util.Stack;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/4/21 16:40
 * @see six.practices
 */
public class Uva673_6_1 {

    private List<String> data = IOUtils.readFromFile("6/1/in2.txt");
    private Stack<Character> stack = new Stack<>();

    private boolean isLegal(String s) {
        stack.clear();
        int l = s.length();
        char c;
        for (int i = 0; i < l; i++) {
            c = s.charAt(i);
            switch (c) {
                case ')':
                    if (stack.isEmpty() || '(' != stack.pop()){
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || '[' != stack.pop()) {
                        return false;
                    }
                    break;
                case ' ':
                    break;
                default:
                    stack.push(c);
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String args[]) {
        Uva673_6_1 uva = new Uva673_6_1();
        for (String s : uva.data) {
            System.out.println(uva.isLegal(s));
        }
    }
}
