package six;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: six
 * author: Chris
 * date: 2018/4/10 16:36
 */
public class Uva548_6_8 {

    private Node bTree;
//    private int[] inorder = {3, 2, 1, 4, 5, 7, 6};
//    private int[] inorder = {7, 8, 11, 3, 5, 16, 12, 18};
    private int[] inorder = {255};
//    private int[] postorder = {3, 1, 2, 5, 6, 7, 4};
//    private int[] postorder = {8, 3, 11, 7, 16, 18, 12, 5};
    private int[] postorder = {255};
    private int lowestVal;
    private int lowestSum = -1;

    private boolean isLeaf(Node node) {
        return null == node.left && null == node.right;
    }

    private void dfs(Node root, int sum) {
        if (null == root) return;
        sum += root.val;
        if (isLeaf(root) && (lowestSum == -1 || sum < lowestSum || (sum == lowestSum && lowestVal > root.val))) {
            lowestVal = root.val;
            lowestSum = sum;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
    }

    private void buildTree(Node root, int inStart, int inEnd, int postStart, int postEnd) {
        root.val = postorder[postEnd];
        for (int i = inStart; i <= inEnd; i++) {
            if (root.val == inorder[i]) {
                if (i > inStart) {
                    buildTree(root.getLeft(), inStart, i - 1, postStart, postStart + (i - inStart - 1));
                }
                if (i < inEnd) {
                    buildTree(root.getRight(), i + 1, inEnd, postStart + (i - inStart), postEnd - 1);
                }
            }
        }
    }

    public static void main(String args[]) {

        Uva548_6_8 uva = new Uva548_6_8();
        uva.buildTree(uva.bTree = new Node(), 0, uva.inorder.length - 1, 0, uva.postorder.length - 1);
        uva.dfs(uva.bTree, 0);
        System.out.println(uva.lowestVal);
    }
}
