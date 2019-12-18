package json_reader_writer;

import member_manager.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
//This is only a template for json simple 1.1.1, we will have to refqctor it but it is here at the moment
//for us to understand how it works.
//TODO: Refactor this for our code so that information read from the Milestones.txt are correctly printed on print.

public class JsonWriter {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
    public void savePlanner(Planner planner){
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy LL dd");
        JSONObject plannerObject = new JSONObject();
        plannerObject.put("projectName", planner.getProjectName());
        plannerObject.put("startDate", planner.getStartDate().format(formatter));
        plannerObject.put("endDate", planner.getEndDate().format((formatter)));
        plannerObject.put("budget", Double.toString(planner.getBudget()));
        JSONArray memberList = new JSONArray();
        //JSONObject memberObject = new JSONObject();
        //memberObject.put("members", memberList);

        for(Member member: planner.members){
            JSONObject memberDetails = new JSONObject();
            memberDetails.put("firstName", member.getFirstName());
            memberDetails.put("lastName", member.getLastName());
            memberDetails.put("ID", Integer.toString(member.getId()));
            memberDetails.put("salary", Double.toString(member.getSalary()));
            JSONArray timesheetList = new JSONArray();

            for(Timesheet timesheet:member.getHours()){
                JSONObject timesheetDetails = new JSONObject();
                timesheetDetails.put("week",timesheet.getWeek());
                timesheetDetails.put("hours", timesheet.getHours());
                timesheetList.add(timesheetDetails);
                memberDetails.put("hoursWorked",timesheetList);
            }
            memberList.add(memberDetails);
            plannerObject.put("members", memberList);

        }

        JSONArray milestoneList = new JSONArray();

        for(Milestone milestone:planner.milestones){
            JSONObject milestoneDetails = new JSONObject();
            milestoneDetails.put("milestoneName", milestone.getMilestoneName());
            milestoneDetails.put("milestoneDescription", milestone.getMilestoneDescription());
            milestoneDetails.put("startDate", milestone.getStartDate().format(formatter));
            milestoneDetails.put("plannedEndDate", milestone.getPlannedEndDate().format(formatter));
            milestoneDetails.put("actualEndDate", milestone.getActualEndDate().format(formatter));

            JSONArray hoursPerMember = new JSONArray();
            JSONObject hoursWorkedDetails = new JSONObject();
            Map<Integer, Double> map = milestone.getEmployeeHours();
            for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                hoursWorkedDetails.put(entry.getKey().toString(), entry.getValue().toString());
                //JSONObject hoursWorkedDetails = new JSONObject();

            }
            milestoneDetails.put("hoursPerMember", hoursWorkedDetails);
            milestoneList.add(milestoneDetails);
            plannerObject.put("milestones" ,milestoneList);

        }


        JSONArray riskMatrixList = new JSONArray();

        for(RiskMatrix riskMatrix : planner.risks){
            JSONObject riskMatrixDetails = new JSONObject();
            riskMatrixDetails.put("riskName", riskMatrix.getRiskName());
            riskMatrixDetails.put("veryLikely", riskMatrix.getVeryLikely());
            riskMatrixDetails.put("possible", riskMatrix.getPossible());
            riskMatrixDetails.put("unlikely", riskMatrix.getUnlikely());
            riskMatrixList.add(riskMatrixDetails);
        }
        plannerObject.put("riskMatrix", riskMatrixList);


        try (FileWriter file = new FileWriter("input.json")){
            file.write(plannerObject.toJSONString());
            //file.write(memberList.toJSONString());
            file.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /*public static void main(String[] args) throws FileNotFoundException {

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

    }*/
}

