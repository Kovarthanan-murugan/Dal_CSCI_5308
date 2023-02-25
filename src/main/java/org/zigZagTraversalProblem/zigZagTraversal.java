package org.zigZagTraversalProblem;
public class zigZagTraversal {
    public static zigZagNodes createBinaryTree(int[] values, int index) {
        if (index >= values.length || values[index] == -1) {
            return null;
        }

        zigZagNodes node = new zigZagNodes(values[index]);
        node.left = createBinaryTree(values, 2 * index + 1);
        node.right = createBinaryTree(values, 2 * index + 2);
        return node;
    }

    public int getHeight(zigZagNodes node) {
        System.out.println("inside height");
        if (node == null) {
            return 0;
        }
        System.out.println("beforeleft");
        int leftHeight = getHeight(node.left);
        System.out.println("leftHeight-->"+leftHeight);
        int rightHeight = getHeight(node.right);
        System.out.println("rightHeight-->"+rightHeight);

        return Math.max(leftHeight,rightHeight)+1;
    }


    public static void main(String[] args) {


    }


}