package org.anagramproblem;

import java.util.Arrays;
public class anagram {
    public boolean isAnagram(String str1,String str2){
        boolean result=true;

        //if length of the strings are not same, don't proceed, return false
        if(str1.length() != str2.length() ){
            return false;
        }

        //Convert string to character array to compare each character
        char [] char_set_str_1 = str1.toCharArray();
        char [] char_set_str_2 = str2.toCharArray();

        //Sort both the arrays
        Arrays.sort(char_set_str_1);
        Arrays.sort(char_set_str_2);

        for(int i=0;i<str1.length();i++){
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