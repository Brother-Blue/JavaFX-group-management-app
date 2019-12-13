package json_reader_writer;

import member_manager.Member;
import member_manager.Milestone;
import member_manager.Planner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

public class JsonReader {

    JSONParser jsonParser = new JSONParser();

    public Planner loadPlanner() {

        //JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            //extracting the planner details from Json and creating object.
            String projectName = (String) jsonObject.get("projectName");
            LocalDate startDate = LocalDate.parse((String) jsonObject.get("startDate"));
            LocalDate endDate = LocalDate.parse((String) jsonObject.get("endDate"));
            int budget = (int) jsonObject.get("budget");
            Planner planner = new Planner(projectName, startDate, endDate, budget);
            return planner;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Member loadMembers() {
        try {
            //Parsing the contents of the JSON file
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray memberArray = (JSONArray) jsonObject.get("members");
            Iterator<JSONObject> iterator = memberArray.iterator();
            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                String firstName = (String) object.get("firstName");
                String lastName = (String) object.get("lastName");
                int dateOfBirth = (int) object.get("dateOfBirth");
                int salary = (int) object.get("salary");
                Member member = new Member(firstName, lastName, dateOfBirth, salary);
                return member;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Milestone loadMilestones(){
        try {
            //Parsing the contents of the JSON file
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray milestoneArray = (JSONArray) jsonObject.get("milestones");
            Iterator<JSONObject> iterator1 = milestoneArray.iterator();
            while (iterator1.hasNext()) {
                JSONObject object = iterator1.next();
                String milestoneName = (String) object.get("milestoneName");
                boolean accomplished = (boolean) object.get("accomplished");
                String milestoneDescription = (String) object.get("milestoneDescription");
                Member member = (Member) object.get("member"); //dont know how we will solve this just yet.
                Milestone milestone = new Milestone(milestoneName, milestoneDescription, member, accomplished);
                return milestone;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}