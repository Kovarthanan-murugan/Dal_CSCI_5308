package org.anagramproblem;

import java.util.Arrays;
public class anagram {
    /*Our definition of Anagram :
        -> can be a word, title phrase, or sentence
        -> the letters are re-arranged and each letter is  used only once
        -> no letter is skipped, don't consider White-spaces , not Case-sensitive
        -> may include numbers as well
      Reference : https://word.tips/faq/
    */

    /**
     * This method is used to check if the given two strings are Anagram or not.
     * @param   str1 String 1.
     * @param   str2 String 2.
     * @return  TRUE : is an Anagram ;
     *          FALSE : is NOT an Anagram ; .
     */

    public boolean isAnagram(String str1,String str2){

        boolean result=true;

        //Remove white spaces in the strings, because they are also considered as characters while calculating length
        str1 = str1.replaceAll("\\s+", "");
        str2 = str2.replaceAll("\\s+", "");

        //if length of the strings are not same, don't proceed, return false
        if(str1.length() != str2.length() ){
            return false;
        }

        //convert strings to lowercase
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        //Convert string to character array to compare each character
        char [] char_set_str_1 = str1.toCharArray();
        char [] char_set_str_2 = str2.toCharArray();

        //Sort both the arrays
        Arrays.sort(char_set_str_1);
        Arrays.sort(char_set_str_2);

        for(int i=0;i<str1.length();i++){
            /* If characters at index 'i' in both the strings are same:
                    Continue and move to next index
               Else:
                    Is not an Anagram -> make result False -> Break the loop
            */
            if(char_set_str_1[i] == char_set_str_2[i] ){
                continue;}
            else{
                result = false;
                break;
            }
        }
        return result;
    }
     public static void main(String args[]){

        }


}