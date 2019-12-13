/*package json_reader_writer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import member_manager.Member;
import member_manager.Milestone;
import member_manager.Planner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

    public Planner loadPlanner() {
        JSONParser jsonParser = new JSONParser();

        try {
            //Parsing the contents of the JSON file
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            //extracting the planner details from Json and creating object.
            String projectName = (String) jsonObject.get("projectName");
            LocalDate startDate = LocalDate.parse((String) jsonObject.get("startDate"));
            LocalDate endDate = LocalDate.parse((String) jsonObject.get("endDate"));
            int budget = Integer.parseInt((String)jsonObject.get("budget"));
            return new Planner(projectName, startDate, endDate, budget);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        public ArrayList<Member> loadMember(){
        JSONParser jsonParser = new JSONParser();
        ArrayList<Member> members = new ArrayList<>();

        try {
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray memberArray = (JSONArray) jsonObject.get("members");
            Iterator<JSONObject> iterator = memberArray.iterator();
            while (iterator.hasNext()) {
                JSONObject object = iterator.next();
                String firstName = (String) object.get("firstName");
                String lastName = (String) object.get("lastName");
                int dateOfBirth = Integer.parseInt((String) object.get("dateOfBirth"));
                int salary = Integer.parseInt((String) object.get("salary"));
                Member member = new Member(firstName, lastName, dateOfBirth, salary);
                members.add(member);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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
            Iterator<JSONObject> iterator1 = milestoneArray.iterator();
            while (iterator1.hasNext()) {
                JSONObject object = iterator1.next();
                String milestoneName = (String) object.get("milestoneName");
                boolean accomplished = Boolean.parseBoolean((String) object.get("accomplished"));
                LocalDate startDate = LocalDate.parse((String) jsonObject.get("startDate"));
                LocalDate endDate = LocalDate.parse((String) jsonObject.get("endDate"));
                String milestoneDescription = (String) object.get("milestoneDescription");
                Milestone milestone = new Milestone(milestoneName, startDate, endDate, milestoneDescription, loadMember());
                milestones.add(milestone);
            }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
        return milestones;
    }
}