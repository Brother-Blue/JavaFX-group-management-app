package json_reader_writer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import member_manager.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

    public Planner loadPlanner() {
        JSONParser jsonParser = new JSONParser();
        Planner planner = null;
        try {
            //Parsing the contents of the JSON file
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            //extracting the planner details from Json and creating object.
            String projectName = (String) jsonObject.get("projectName");
            LocalDate startDate = LocalDate.parse((String) jsonObject.get("startDate"));
            LocalDate endDate = LocalDate.parse((String) jsonObject.get("endDate"));
            double budget = Double.parseDouble((String)jsonObject.get("budget"));
            planner = new Planner(projectName, startDate, endDate, budget);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return planner;
    }

        //Reader and object creator for the members
        public ArrayList<Member> loadMember(){
        JSONParser jsonParser = new JSONParser();
        ArrayList<Member> members = new ArrayList<>();

        try {
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray memberArray = (JSONArray) jsonObject.get("members");

            for (JSONObject object : (Iterable<JSONObject>) memberArray) {
                String firstName = (String) object.get("firstName");
                String lastName = (String) object.get("lastName");
                float ID = Integer.parseInt((String) object.get("ID"));
                double salary = Double.parseDouble((String) object.get("salary"));
                Member member = new Member(firstName, lastName, ID, salary);
                members.add(member);
                System.out.println(member.getId() + ": " + member.getFirstName());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
            return members;
    }


    public ArrayList<Milestone> loadMilestone(){

        JSONParser jsonParser = new JSONParser();
        ArrayList<Milestone> milestones = new ArrayList<>();

        try {
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray milestoneArray = (JSONArray) jsonObject.get("milestones");

            for (JSONObject object : (Iterable<JSONObject>) milestoneArray) {
                String milestoneName = (String) object.get("milestoneName");
                String milestoneDescription = (String) object.get("milestoneDescription");
                LocalDate startDate = LocalDate.parse((String) object.get("startDate"));
                LocalDate plannedEndDate = LocalDate.parse((String) object.get("plannedEndDate"));
                LocalDate actualEndDate = LocalDate.parse((String) object.get("actualEndDate"));
                JSONObject hoursPerMember = (JSONObject) object.get("hoursPerMember");


                Milestone milestone = new Milestone(milestoneName, milestoneDescription, startDate, plannedEndDate, actualEndDate, createMembers(hoursPerMember));
                milestones.add(milestone);
            }
    } catch (ParseException | IOException e) {
        e.printStackTrace();
    }
        return milestones;
    }

    public Map<Integer, Map<Integer, Double>> loadTimesheet() {
        JSONParser jsonParser = new JSONParser();
        Map<Integer, Map<Integer, Double>> timesheets = new HashMap<>();

        try {
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray timesheetArray = (JSONArray) jsonObject.get("timesheet");

            for (JSONObject object : (Iterable<JSONObject>) timesheetArray) {
                int weekNumber = Integer.valueOf((String) object.get("week"));
                JSONObject getHoursWorked = (JSONObject) object.get("hoursWorked");
                Map<Integer, Double> membersWithHours = createMembers(getHoursWorked);
                timesheets.put(weekNumber, membersWithHours);
            }
        } catch (ParseException | IOException | InputMismatchException e) {
            e.printStackTrace();
        }
        System.out.println(timesheets);
        return timesheets;
    }

    public ArrayList<Double> percentComplete() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Double> percentages = new ArrayList<>();

        try {
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray timesheetArray = (JSONArray) jsonObject.get("timesheet");

            for (JSONObject object : (Iterable<JSONObject>) timesheetArray) {
                double percent = Double.parseDouble((String) object.get("percentComplete"));
                percentages.add(percent);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return percentages;
    }

    private Map<Integer, Double> createMembers(JSONObject milestone) {
        Map<Integer, Double> map = new HashMap<>();

        for (Object o : milestone.keySet()) {
            Integer key = Integer.valueOf((String) o);
            double value = Double.parseDouble((String) milestone.get(key.toString()));
            map.put(key, value);
        }
        return map;
    }

    //TODO: Visualise this information in the input.json with javafx
    //Reader and object creator for the risk matrix
    public ArrayList<RiskMatrix> loadRiskMatrix(){

        JSONParser jsonParser = new JSONParser();
        ArrayList<RiskMatrix> risks = new ArrayList<>();

        try{
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray milestoneArray = (JSONArray) jsonObject.get("riskMatrix");

            for (JSONObject object : (Iterable<JSONObject>) milestoneArray) {
                String riskName = (String) object.get("riskName");
                String veryLikely = (String) object.get("veryLikely");
                String possible = (String) object.get("possible");
                String unlikely = (String) object.get("unlikely");

                RiskMatrix risk = new RiskMatrix(riskName, veryLikely, possible, unlikely);
                risks.add(risk);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return risks;

    }

}