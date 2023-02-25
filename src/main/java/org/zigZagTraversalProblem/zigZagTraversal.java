package org.zigZagTraversalProblem;
public class zigZagTraversal {
    int[] travedFinal;
    int travedCount =0;
    public static zigZagNodes createBinaryTree(int[] values, int index) {
        if (index >= values.length || values[index] == -1) {
            return null;
        }

        zigZagNodes node = new zigZagNodes(values[index]);
        node.left = createBinaryTree(values, 2 * index + 1);
        node.right = createBinaryTree(values, 2 * index + 2);
        return node;
    }
    public int[]  zigzagLevelOrder(zigZagNodes root,int count) {
        System.out.println("inside zizag");
        if (root == null) {
            return null;
        }
        travedFinal = new int[count];

        boolean isZigzag = false;

        int height = getHeight(root);

        for (int level = 1; level <= height; level++) {
            printLevel(root, level, isZigzag);
            isZigzag = !isZigzag;
        }
        return travedFinal;
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
    private void printLevel(zigZagNodes node, int level, boolean isZigzag) {
        if (node == null) {
            return;
        }

        if (level == 1) {
            System.out.print(node.val + " ");
            travedFinal[travedCount] = node.val;
            travedCount++;
        } else if (level > 1) {
            if (isZigzag) {
                printLevel(node.right, level - 1, isZigzag);
                printLevel(node.left, level - 1, isZigzag);
            } else {
                printLevel(node.left, level - 1, isZigzag);
                printLevel(node.right, level - 1, isZigzag);
            }
        }
    }


    public static void main(String[] args) {


    }


}