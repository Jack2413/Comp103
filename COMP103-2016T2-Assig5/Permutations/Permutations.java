// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name:Zhancheng Gan
 * Usercode:
 * ID:300378961
 */

import ecs100.*;
import java.util.*;

/** 
 *  Prints out all permuations of a string
 *  The static method permute constructs all the permutations.permutation
 *  The main method gets the string, calls recPermute, and prints the result.
 */
public class Permutations {
    private static List <String> StringList = new ArrayList <String>();    

    private static String OriginalString;
    private static String NewString;    
    private static String LastString;
    private static long time;

    /**
     * @return a List of all the permutations of a String. 
     */
    public static List <String> recPermute(String string) {
        OriginalString=string; //the string that we enter in 
        LastString=string; 

        long start = System.nanoTime();

        StringList = new ArrayList <String>(); // create a list for return 
        StringList.add(string); // add the string in

        Sorting(string); // start permutation
        time = System.nanoTime() - start; 

        return StringList;

    }

    public static void Sorting (String string){

        for(int i= 0;i<string.length();i++){//permutation
            String permutation= (string.substring(0,i)+string.substring(i+1,string.length())); // get one out place at front
            NewString =string.charAt(i)+permutation; // NewString

            String finalstring = fix(permutation.length(),LastString)+NewString; // become the finalstring

            if(i!=0){// to Avoid unnecessary repetition
                StringList.add(finalstring);
                LastString=finalstring;
            }

            if(permutation.length()>1){ // if the string >1 still need to sorting
                Sorting(permutation); //to sorting the permutation string
            }

        }
    }

    public static String fix(int length,String string){ //to fix the string 
        if (NewString.length()==OriginalString.length())return "";// if is the same length dont need to return
        else{
            return string.substring(0,(string.length()-length-1));// add the char that didnt change
        }
    }

    // Main
    public static void main(String[] arguments){
        UI.initialise();
        UI.setWindowSize(500,400);
        UI.setDivider(1);
        String string = "";

        while (! string.equals("#")) {
            string = UI.askString("Enter string to permute - # to exit: ");
            if (string.length() < 11) {

                List<String> permutations = recPermute(string);

                for (String p : permutations)
                    UI.println(p);
                UI.println("Time used: "+ time/1000+"ms");
                UI.println("---------");

            }
            else UI.println("Give a smaller string.");
        }
        UI.quit();
    }    
}
