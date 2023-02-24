package assignmentTest;

import org.anagramproblem.anagram;
import static org.junit.Assert.*;
import org.junit.Test;
public class anagramTest {
    @Test
    public void IsAnagramTest(){
        //Given
        String firstString= "study";
        String secondString= "dusty";
        anagram anagram_obj = new anagram();
        //When
        boolean isAnagram = anagram_obj.isAnagram(firstString,secondString);
        //Then
        assertEquals(isAnagram,true);

    }
    @Test
    public void isNotAnagramTest(){
        //Given
        String firstString= "study";
        String secondString= "dust";
        anagram anagram_obj = new anagram();
        //When
        boolean isAnagram = anagram_obj.isAnagram(firstString,secondString);
        //Then
        assertEquals(isAnagram,false);

    }
    @Test
    public void isAnagramNotCaseSensitiveTest(){
        //Given
        String firstString= "desSert";
        String secondString= "stRessed";
        anagram anagram_obj = new anagram();
        //When
        boolean isAnagram = anagram_obj.isAnagram(firstString,secondString);
        //Then
        assertEquals(isAnagram,true);

    }
    @Test
    public void isAnagramSpaceTest(){
        //Given
        String firstString= "the classroom";
        String secondString= "schoolmaster";
        anagram anagram_obj = new anagram();
        //When
        boolean isAnagram = anagram_obj.isAnagram(firstString,secondString);
        //Then
        assertEquals(isAnagram,true);

    }



}
