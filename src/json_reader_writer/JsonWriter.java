package json_reader_writer;

import member_manager.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

// Inspiration for logic of the json reader and writer comes from the following site:
// https://stackabuse.com/reading-and-writing-json-in-java/

public class JsonWriter {

    //Formatter to easily parse Date format to correct string format.
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");

    @SuppressWarnings("unchecked")
    public void savePlanner(Planner planner){
        JSONObject plannerObject = new JSONObject();
        plannerObject.put("projectName", planner.getProjectName());
        plannerObject.put("startDate", planner.getStartDate().format(formatter));
        plannerObject.put("endDate", planner.getEndDate().format((formatter)));
        plannerObject.put("budget", Double.toString(planner.getBudget()));
        JSONArray memberList = new JSONArray();

        for(Member member: planner.members){
            JSONObject memberDetails = new JSONObject();
            memberDetails.put("firstName", member.getFirstName());
            memberDetails.put("lastName", member.getLastName());
            memberDetails.put("ID", Integer.toString(member.getId()));
            memberDetails.put("salary", Double.toString(member.getSalary()));
            JSONArray timesheetList = new JSONArray();

        }

        JSONArray milestoneList = new JSONArray();

        for(Milestone milestone:planner.milestones){
            JSONObject milestoneDetails = new JSONObject();
            milestoneDetails.put("milestoneName", milestone.getMilestoneName());
            milestoneDetails.put("milestoneDescription", milestone.getMilestoneDescription());
            milestoneDetails.put("startDate", milestone.getStartDate().format(formatter));
            milestoneDetails.put("plannedEndDate", milestone.getPlannedEndDate().format(formatter));
            milestoneDetails.put("actualEndDate", milestone.getActualEndDate().format(formatter));

            JSONObject hoursWorkedDetails = new JSONObject();
            Map<Integer, Double> map = milestone.getEmployeeHours();
            for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                hoursWorkedDetails.put(entry.getKey().toString(), entry.getValue().toString());
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
            file.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

