package com.company;
//this is P15.2
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

//this is E5.4
public class P15_2 {
    static class SingleStudent implements Comparable<SingleStudent>{//Single student object
        String firstname;
        String lastname;
        String ID;
        String Grade;

        SingleStudent(String firstname, String lastname ,String ID, String Grade){//constructor
            this.firstname = firstname;
            this.lastname = lastname;
            this.ID = ID;
            this.Grade = Grade;
        }

        String convert(){return lastname + firstname;}//combine last and first name for sort use

        String ReturnFirst(){return firstname;}//return stuff

        String ReturnGrade(){return Grade;}//return stuff

        String ReturnLast(){return lastname;}//return stuff

        String ReturnID(){return ID;}//return stuff

        void SetID(String Grade){this.Grade = Grade;}//set ID

        @Override
        public int compareTo(SingleStudent o) {//Compare method
            if(this.convert().compareToIgnoreCase(o.convert()) == 0){// if name equal
                return ID.compareToIgnoreCase(o.ID);
            }
            else{
                return this.convert().compareToIgnoreCase(o.convert());
            }
        }

        public String PrintAll(){//pringout
            return (firstname + " " + lastname + " (ID=" + ID + ")");
        }
    }
    static class Students{
        Map<String, SingleStudent> StudentMap = new HashMap<String, SingleStudent>();//for search, modify use
        Map<SingleStudent, String> OrderMap = new HashMap<SingleStudent, String>();//for printout use

        Students(){}

        void add(String firstname, String lastname, String ID, String grade){//add on both
            SingleStudent obj = new SingleStudent(firstname, lastname, ID, grade);
            if(StudentMap.containsKey(ID)){//if contains
                System.out.println("Contained ID, please try again");
            }
            else{
                StudentMap.put(ID, obj);
                OrderMap.put(obj, grade);
            }
        }

        void remove(String name){
            SingleStudent obj = StudentMap.get(name);
            if(StudentMap.remove(name) == null){//if does not exist
                System.out.println("No such student exist");
            }
            else{
                OrderMap.remove(obj);
                System.out.println("Delete successfully");
            }
        }

        void modifyGrade(String ID, String grade){
            SingleStudent obj = StudentMap.get(ID);
            obj.SetID(grade);
            StudentMap.replace(ID, obj);
            if(StudentMap.replace(ID, obj) == null){//replace the obj
                System.out.println("No such student exist");
            }
            else{
                OrderMap.replace(obj, obj.ReturnGrade());
                System.out.println("Modified successfully");
            }
        }

        void printall(){//referenced by: https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
            // TreeMap to store values of HashMap
            TreeMap<SingleStudent, String> sorted = new TreeMap<>();

            // Copy all data from hashMap into TreeMap
            sorted.putAll(OrderMap);

            // Display the TreeMap which is naturally sorted
            for (Map.Entry<SingleStudent, String> entry : sorted.entrySet())
                System.out.println(entry.getKey().PrintAll() + ": " + entry.getValue());
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
                System.out.print("Please input the firstname: ");
                String firstname = Userin.nextLine();
                System.out.print("Please input the lastname: ");
                String lastname = Userin.nextLine();
                System.out.print("Please input the ID: ");
                String ID = Userin.nextLine();
                System.out.print("Please input the grade: ");
                String grade = Userin.nextLine();
                StuObj.add(firstname.substring(0, 1).toUpperCase() + firstname.substring(1).toLowerCase(), lastname.substring(0, 1).toUpperCase() + lastname.substring(1).toLowerCase(), ID, grade);
            }
            else if(input.charAt(0) == 'r'){
                System.out.print("Please input the ID: ");
                String name = Userin.nextLine();
                StuObj.remove(name);
            }
            else if(input.charAt(0) == 'm'){
                System.out.print("Please input the ID: ");
                String name = Userin.nextLine();
                System.out.print("Please input the grade: ");
                String grade = Userin.nextLine();
                StuObj.modifyGrade(name, grade);
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
