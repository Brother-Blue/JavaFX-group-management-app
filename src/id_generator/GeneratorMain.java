package id_generator;

public class GeneratorMain {

    private static final int SHORTEN_ID = 10;

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
        generatedID = ((int) (concatID / Math.sqrt(charsInName)+(Math.sqrt(birthday)))) / SHORTEN_ID ;
        System.out.println(">> Generated ID: " + generatedID);

        return generatedID;
    }

    //inspiration from https://stackoverflow.com/questions/6456219/java-checking-if-parseint-throws-exception
    public static boolean isParsable(String inputText) {
        try {
            Integer.parseInt(inputText);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}
