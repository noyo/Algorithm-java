package six;

import util.IOUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: six
 * author: Chris
 * date: 2018/4/9 14:59
 */
public class Uva122_6_7 {

    private List<String> data = IOUtils.readFromFile("6/7/in.txt");
    private Node root;

    private void bfs(Node bTree) {
        if (null == bTree) return;
        LinkedList<Node> queue = new LinkedList<>();
        queue.push(bTree);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.val + " ");
            if (null != node.left) {
                queue.addLast(node.left);
            }
            if (null != node.right) {
                queue.addLast(node.right);
            }
        }
        System.out.println();
        root = null;
    }

    private void insertNode(int val, String route) {
        if (null == root) root = new Node();
        Node node = root;
        int size = route.length();
        for (int i = 0; i < size; i++) {
            if (route.charAt(i) == 'L') {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        node.val = node.valued ? -1 : val;
        node.valued = true;
    }

    public static void main(String args[]) {

        Uva122_6_7 uva = new Uva122_6_7();
        for (String s : uva.data) {
            String ss[] = s.split(",");
            uva.insertNode(Integer.parseInt(ss[0].substring(1)), ss[1].substring(0, ss[1].length() - 1));
        }
        uva.bfs(uva.root);

    }



}
