package org.zigZagTraversalProblem;
public class zigZagTraversal {
    public static zigZagNodes createBinaryTree(int[] values, int index) {
        if (index >= values.length || values[index] == -1) {
            return null;
        }

        zigZagNodes node = new zigZagNodes(values[index]);

        return node;
    }



    public static void main(String[] args) {


    }


}