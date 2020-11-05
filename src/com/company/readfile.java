package com.company;
//this is E5.15
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class readfile {

    public static void main(String[] args) throws FileNotFoundException{
        File Fileobj = new File("Main.java");// file reader
        Scanner fin = new Scanner(Fileobj);
        ArrayList<String> myList = new ArrayList<String>();//List to store the sentences
        ArrayList<String> myWord = new ArrayList<String>();//List to store the words
        Map<String, String> myMap = new HashMap<String, String>();//map to store the words and regarded lines
        while(fin.hasNextLine()){//storing all the sentences into myList
            String sentence = fin.nextLine();
            if(!sentence.equals("")){
                myList.add(sentence);
            }
        }
        fin.close();//close the file
        for(String e:myList){
            String list[] = e.split("[^A-Za-z0-9_]+");//spilt
            for(String temp:list){
                if(!temp.equals("")){
                    Map<String, String> myTempMap = new HashMap<String, String>();
                    myTempMap.put(temp, e);//put in temporary map
                    myTempMap.forEach(//Merge referenced by: https://www.geeksforgeeks.org/hashmap-mergekey-value-bifunction-method-in-java-with-examples/
                            (key, value)//merge the new and the old map
                                    -> myMap.merge(
                                    key,
                                    value,
                                    (v1, v2)
                                            -> v1.equalsIgnoreCase(v2)
                                            ? v1
                                            : v1 + "\n" + v2));
                }
            }
            myWord.addAll(Arrays.asList(list));//store all the words
        }
        Set<String> mySet = new LinkedHashSet<String>();//store all the words without duplicate
        mySet.addAll(myWord);
        int count = 0;
        for(String e:mySet){
            if(!e.equals("")){//output everything
                System.out.println(count + ": " + e + " occurs in:");
                System.out.println(myMap.get(e));
                System.out.println("");
                count++;
            }
        }
    }
}
