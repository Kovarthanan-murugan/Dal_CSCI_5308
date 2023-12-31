package org.zigZagTraversalProblem;

public class zigZagTraversal {
    int[] traversedFinal;
    int traversedCount =0;
    /**
     * This method is used to build a binarytree with the given array of values.
     * @param values array of values.
     * @param  index size of the array
     * @return objects of created binary tree.
     */
    public static zigZagNodes createBinaryTree(int[] values, int index) {
        if (index >= values.length || values[index] == -1) {
            return null;
        }

        zigZagNodes node = new zigZagNodes(values[index]);
        node.left = createBinaryTree(values, 2 * index + 1);
        node.right = createBinaryTree(values, 2 * index + 2);
        return node;
    }
    /**
     * This method is used to delete empty values in the output.
     * @param traversedList zigzag traversed array
     * @return zigzag traversed array with no empty values.
     */
    private int [] removeNullValues(int[] traversedList) {

        int[] traversedListCopy = new int[traversedList.length];
        int j =-1,countOfNull = 0;
        int[] removeNullValuesFinal=new int[1];
        for (int i = 0, k = 0; i < traversedList.length; i++) {
            if (traversedList[i] != j) {
                traversedListCopy[k] = traversedList[i];
                k++;
            }
            else
            {
                countOfNull++;
            }

            removeNullValuesFinal =  new int[traversedList.length-countOfNull];
            for (int r=0;r<traversedList.length-countOfNull;r++){
                removeNullValuesFinal[r] = traversedListCopy[r];

            }

        }
        return removeNullValuesFinal;
    }
    /**
     * This method is used to traverse the tree in the zigzag order and print the node value.
     * @param root objects of the binary tree.
     * @param  count size of the array
     * @return zigzag traversed values of given tree.
     */
    public int[]  zigzagLevelOrder(zigZagNodes root,int count) {
        if (root == null) {
            return null;
        }
        //Initialize traversedFinal
        traversedFinal = new int[count];

        boolean isZigzag = false;

        int height = getHeight(root);

        for (int level = 1; level <= height; level++) {
            //Populate traversedFinal
            printLevel(root, level, isZigzag);
            isZigzag = !isZigzag;
        }
        return removeNullValues(traversedFinal);
    }
    /**
     * This method is used to get heigth of each node.
     * @param node objects of the binary tree.
     * @return heigth of each node.
     */
    public int getHeight(zigZagNodes node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.max(leftHeight,rightHeight)+1;
    }

    /**
     * This method is used to print values of zigzag traversed nodes.
     * @param node objects of the binary tree.
     * @param level current level of the traversing loop.
     * @param isZigzag indicate direction of the traversal.
     */
    private void printLevel(zigZagNodes node, int level, boolean isZigzag) {
        if (node == null) {
            traversedFinal[traversedCount] = -1;
            traversedCount++;
            return;

        }

        if (level == 1) {
            traversedFinal[traversedCount] = node.val;
            traversedCount++;
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