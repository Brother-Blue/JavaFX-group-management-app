package json_reader_writer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import member_manager.Member;
import member_manager.Milestone;
import member_manager.Planner;
import member_manager.RiskMatrix;
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

        //Reader and object creator for the members
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
                float ID = Integer.parseInt((String) object.get("ID"));
                double salary = Integer.parseInt((String) object.get("salary"));
                Member member = new Member(firstName, lastName, ID, salary);
                members.add(member);
                System.out.println(member.getId() + ": " + member.getFirstName());
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

    //Reader and object creator for the milestones
    public ArrayList<Milestone> loadMilestone(){

        JSONParser jsonParser = new JSONParser();
        ArrayList<Milestone> milestones = new ArrayList<>();

        double hours = 0;
        try {
            Object obj = jsonParser.parse(new FileReader("input.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray milestoneArray = (JSONArray) jsonObject.get("milestones");
            Iterator<JSONObject> iterator1 = milestoneArray.iterator();
            while (iterator1.hasNext()) {
                JSONObject object = iterator1.next();
                String milestoneName = (String) object.get("milestoneName");
                String milestoneDescription = (String) object.get("milestoneDescription");
                LocalDate startDate = LocalDate.parse((String) object.get("startDate"));
                LocalDate plannedEndDate = LocalDate.parse((String) object.get("plannedEndDate"));
                LocalDate actualEndDate = LocalDate.parse((String) object.get("actualEndDate"));
                JSONObject hoursPerEmployee = (JSONObject) object.get("hoursPerEmployee");


                Milestone milestone = new Milestone(milestoneName, milestoneDescription, startDate, plannedEndDate, actualEndDate, createMembers(hoursPerEmployee));
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

    private Map<Integer, Double> createMembers(JSONObject milestone) {
        Map<Integer, Double> map = new HashMap<>();
        Iterator milestoneIterator = milestone.keySet().iterator();

        while (milestoneIterator.hasNext()) {
            Integer key = Integer.valueOf((String) milestoneIterator.next());
            double value = Double.valueOf((String) milestone.get(key.toString()));
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
            Iterator<JSONObject> iterator2 = milestoneArray.iterator();
            while(iterator2.hasNext()){
                JSONObject object = iterator2.next();
                String riskName = (String) object.get("riskNAme");
                String veryLikely = (String) object.get("veryLikely");
                String possible = (String) object.get("possible");
                String unlikely = (String) object.get("unlikely");

                RiskMatrix risk = new RiskMatrix(riskName, veryLikely, possible, unlikely);
                risks.add(risk);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return risks;

    }

}