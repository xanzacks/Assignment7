package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

//this is E15.4
public class E15_4 {
    static class Students{//class object
        Map<String, String> StudentMap = new HashMap<String, String>();

        Students(){}

        void add(String name, String grade){
            if(!StudentMap.containsKey(name)){
                StudentMap.put(name, grade);
            }
            else{
                System.out.println("Duplicate student");
            }
        }//add student

        void remove(String name){//remove a student
            if(StudentMap.remove(name) == null){
                System.out.println("No such student exist");
            }
            else{
                System.out.println("Delete successfully");
            }
        }

        void modifyGrade(String name, String grade){
            if(StudentMap.replace(name, grade) == null){//if does not exist
                System.out.println("No such student exist");
            }
            else{
                System.out.println("Modified successfully");
            }
        }

        void printall(){//referenced by: https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
            // TreeMap to store values of HashMap
            TreeMap<String, String> sorted = new TreeMap<>();

            // Copy all data from hashMap into TreeMap
            sorted.putAll(StudentMap);

            // Display the TreeMap which is naturally sorted
            for (Map.Entry<String, String> entry : sorted.entrySet())
                System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args){
        boolean keeprunning = true;
        Students StuObj = new Students();
        Scanner Userin = new Scanner(System.in);
        String options = "Select an option: \n" +
                "      (a)to add a student\n" +
                "      (r) to remove a student \n" +
                "      (m)to modify a grade\n" +
                "      (p)print all grades\n" +
                "      (q)to quit\n";
        while(keeprunning){
            System.out.print(options);
            String input = Userin.nextLine();
            if(input.charAt(0) == 'a'){
                System.out.print("Please input the name: ");
                String name = Userin.nextLine();
                System.out.print("Please input the grade: ");
                String grade = Userin.nextLine();
                StuObj.add(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(), grade);
            }
            else if(input.charAt(0) == 'r'){
                System.out.print("Please input the name: ");
                String name = Userin.nextLine();
                StuObj.remove(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
            }
            else if(input.charAt(0) == 'm'){
                System.out.print("Please input the name: ");
                String name = Userin.nextLine();
                System.out.print("Please input the grade: ");
                String grade = Userin.nextLine();
                StuObj.modifyGrade(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(), grade);
            }
            else if(input.charAt(0) == 'p'){
                StuObj.printall();
            }
            else if(input.charAt(0) == 'q'){
                keeprunning = false;
            }
            else{
                System.out.println("No such option");
            }
        }
    }
}
