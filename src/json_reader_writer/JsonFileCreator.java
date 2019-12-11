package json_reader_writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
//This is only a template for json simple 1.1.1, we will have to refqctor it but it is here at the moment
//for us to understand how it works.
//TODO: Refactor this for our code so that information read from the Milestones.txt are correctly printed on print.

/*public class JsonFileCreator {
    public static void main(String[] args) throws FileNotFoundException {

        Employee altug = new Employee("altug", "altetmek", 20000929, 20000);

        Employee hjalmar = new Employee("hjalmar", "idk", 19891209, 30000);


        Milestones trello = new Milestones("Trello", LocalDate.of(2019, 12, 7),
                LocalDate.of(2019, 12, 8), LocalDate.of(2019, 12, 9));

        Milestones trello2 = new Milestones("Trello2", LocalDate.of(2019, 12, 7),
                LocalDate.of(2019, 12, 8), LocalDate.of(2019, 12, 9));

        Milestones trello3 = new Milestones("Trello3", LocalDate.of(2019, 12, 7),
                LocalDate.of(2019, 12, 8), LocalDate.of(2019, 12, 9));

        //create json object for employee
        JSONObject employee1 = new JSONObject();
        employee1.put("name", altug.getForename());
        employee1.put("name", hjalmar.getForename());

        //create json object for milestone
        JSONObject milestone1 = new JSONObject();
        JSONObject milestone2 = new JSONObject();
        milestone1.put("Task", trello.toString());
        milestone1.put("Task2", trello2.toString());
        milestone2.put("Task3", trello3.toString());

        //create json array for milestones
        JSONArray milestonesAltug = new JSONArray();
        JSONArray milestonesHjalmar = new JSONArray();

        milestonesAltug.add(milestone1);
        milestonesHjalmar.add(milestone2);

        //add the array with info into employee1
        employee1.put("Altug did tasks", milestonesAltug);
        employee1.put("Hjalmar did tasks", milestonesHjalmar);

        //System.out.println(employee1.toJSONString());

        File file = new File("Milestones.txt");

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(employee1.toJSONString());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }

        System.out.println("File created successfully.");
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder jsonIn = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonIn.append(scanner.nextLine());
            }
            System.out.println(jsonIn.toString());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }

    }
}

*/