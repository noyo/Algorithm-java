package six;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: six
 * author: Chris
 * date: 2018/4/10 17:04
 */
class Node {
    int val;
    Node left;
    Node right;
    boolean valued = false;

    public Node() {}
    public Node(int val) {this.val = val;}

    Node getLeft() {
        if (null == left) {
            left = new Node();
        }
        return left;
    }

    Node getRight() {
        if (null == right) {
            right = new Node();
        }
        return right;
    }
}
