package assignmentTest;

import org.anagramproblem.anagram;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class anagramTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "study","dusty", true, "IsAnagramTest" },
                { "study","dust", false, "isNotAnagramTest"  },
                { "aCt","cAt", true, "isAnagramNotCaseSensitiveTest"},
                { "the classroom","schoolmaster", true,"isAnagramSpaceTest" },
                { "elbow5","5below", true,"isAlphaNumericAnagramTest" }
        });
    }

    private String firstString;
    private String secondString;
    private boolean ouputExpected;
    private String testName;
    public anagramTest(String firstInput, String secondInput,boolean inputExpected,String testName) {
        this.firstString = firstInput;
        this.secondString = secondInput;
        this.ouputExpected = inputExpected;
        this.testName = testName;
    }
    @Test
    public void anagramTests(){
        System.out.println("Test:"+testName);
        anagram anagram_obj = new anagram();
        //When
        boolean isAnagram = anagram_obj.isAnagram(firstString,secondString);
        //Then
        assertEquals(isAnagram,ouputExpected);
    }
}
