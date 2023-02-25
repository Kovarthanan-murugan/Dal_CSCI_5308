package zigZagTraversalProblemTest;

import org.zigZagTraversalProblem.zigZagTraversal;
import org.zigZagTraversalProblem.zigZagNodes;
import org.junit.Test;
import static org.junit.Assert.*;


public class zigZagTraversalTest {

    @Test
    public void isRootIsEmpty(){
        //Given
        int[] values = {};
        //When
        zigZagTraversal traversal = new zigZagTraversal();
        zigZagNodes root = traversal.createBinaryTree(values, 0);
        //Then
        assertEquals(root,null);
    }

    @Test
    public void isRootIsNotEmpty(){
        //Given
        int[] values = {1};
        //When
        zigZagTraversal traversal = new zigZagTraversal();
        zigZagNodes root = traversal.createBinaryTree(values, 0);
        //Then
        assertNotEquals(root,null);
    }

    @Test
    public void getHeightofBinaryTreeTest(){
        //Given
        int[] values = {1,2,3};
        //When
        zigZagTraversal traversal = new zigZagTraversal();
        zigZagNodes root = traversal.createBinaryTree(values, 0);
        int height = traversal.getHeight(root);
        //Then
        assertEquals(height,2);
    }
}