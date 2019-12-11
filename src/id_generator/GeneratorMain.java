package id_generator;

import java.util.Scanner;
import java.util.ArrayList;

public class GeneratorMain {

    //private static ArrayList<id_generator.Digit5> generator5 = new ArrayList<>(); //For storing the ID
    public static Scanner scanner = new Scanner(System.in);

    //Prompt the user for member information
   /* public static void createmember() {
        String firstName = inputString(">> What is the member's first name: ");
        String lastName = inputString(">> What is the member's last name: ");
        String maskID = inputString(">> What is the member's preferred nickname: ");
        int birthday = inputInt(">> Enter member's birthday (YYYYMMDD): ");

        //Create the member's new ID and add it to the ArrayList
        Digit5 ID = new Digit5(firstName, lastName, maskID, birthday, generateID(firstName, lastName, birthday));
        generator5.add(ID);

        //good ol feedback to know the adding didnt crash
        System.out.println("\n>> Member added: " + ID.getFirstName() + " " + ID.getLastName());
        System.out.println(">> Generating member ID.......");
        System.out.println(">> Done!");
    }*/

    //method to create the member's unique ID given their information
    public static int generateID(String firstName, String lastName, int birthday) {
        int generatedID = 0;
        int firstNameSum = 0;
        int lastNameSum = 0;
        int charsInName = firstName.length() + lastName.length();
        char letter;

        //converts first and last names to ASCII values
        System.out.println("\n>> Analyzing first name...");
        for (int i = 0; i < firstName.length(); i++) {
            letter = firstName.charAt(i);
            System.out.print("[" + letter + "] --> ");
            System.out.println("[" + (int) letter + "]");
            firstNameSum = firstNameSum + (int) letter;
        }

        System.out.println("\n>> Analyzing last name...");
        for (int i = 0; i < lastName.length(); i++) {
            letter = lastName.charAt(i);
            System.out.print("[" + letter + "] --> ");
            System.out.println("[" + (int) letter + "]");
            lastNameSum = lastNameSum + (int) letter;
        }

        //come debug code to double check math, TODO: COMMENT OUT BEFORE IMPLEMENTATION
        System.out.println("\n>> First name ID: " + firstNameSum);
        System.out.println(">> Last name ID: " + lastNameSum);

        String concatIDString = (Integer.toString(firstNameSum) + Integer.toString(lastNameSum));
        int concatID = Integer.parseInt(concatIDString);
        System.out.println(">> Concatenated ID: " + concatID);

        //doMath();
        generatedID = ((int) (concatID / Math.sqrt(charsInName)+(Math.sqrt(birthday)))) / 10;

        return generatedID;
    }


    public static String inputString(String message) {
        System.out.print(message);
        String result = scanner.nextLine();
        return result;
    }

    public static int inputInt(String message) {
        System.out.print(message);
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

   /* public static void main(String[] args) {
        int memberAmount = inputInt(">> How many members are in your team: ");
        while (memberAmount > 0) {
            memberAmount--;
            createEmployee();
        }
        System.out.println(generator5); //debug to check if all ID's were added
        scanner.close();
    }*/
}
