package zigZagTraversalProblemTest;

import org.zigZagTraversalProblem.zigZagTraversal;
import org.zigZagTraversalProblem.zigZagNodes;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class zigZagTraversalTest  {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[]{}, null, "isRootIsEmpty","assertEquals"},
                { new int[] {1}, null ,"isRootIsNotEmpty","assertNotEquals"},
                { new int[] {1,2,3}, new int[]{2} , "getHeightofBinaryTreeTest","assertArrayEquals"},
                { new int[] {1,2,3,4,5,6,7}, new int[] {1,3,2,4,5,6,7}, "forFullBinaryTreeTest","assertArrayEquals" },
                { new int[] {1,2,3,-1,5,6,-1}, new int[] {1,3,2,5,6}, "forSomeNodesWithNoChildTest","assertArrayEquals"}
        });
    }

    //@Parameter(0)
    private int[] values = {};

    //@Parameter(1)
    private int[] final_list = {};
    //@Parameter(2)
    private String testName;
    //@Parameter(3)
    private String assertType;

    public zigZagTraversalTest (int[] values, int[] final_list, String testName, String assertType) {
        this.values = values;
        this.final_list = final_list;
        this.testName = testName;
        this.assertType=assertType;
    }
    @Test
    public void zigZagTraversalTests(){
        //Given
        System.out.println("Test:"+testName);
        zigZagTraversal traversal = new zigZagTraversal();
        //zigZagTraversalTest test = new zigZagTraversalTest(null,null,"","");
        zigZagNodes root = traversal.createBinaryTree(values, 0);
        int[] tree_height = new int[]{traversal.getHeight(root)};
        //When

        int[] traversed_list = traversal.zigzagLevelOrder(root,values.length);
        //Then

        System.out.println(assertType);
        if(assertType.equals("assertEquals")){
            assertEquals(traversed_list, final_list);
        }
        else if(assertType.equals("assertNotEquals")){
            assertNotEquals(traversed_list,final_list);
        } else if (assertType.equals("assertArrayEquals")) {
            if(testName.equals("getHeightofBinaryTreeTest")){
                assertArrayEquals(tree_height,final_list);
            }
            else{
                assertArrayEquals(traversed_list,final_list);
            }
        }
    }
}