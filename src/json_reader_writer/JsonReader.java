package json_reader_writer;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

    public Planner loadPlanner() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("input.json");
        Object obj = parser.parse(reader);

        JSONObject jsonObject = (JSONObject) obj;
        String projectName = (String) jsonObject.get("projectName");
        LocalDate startDate = LocalDate.parse((String) jsonObject.get("startDate"));
        LocalDate endDate = LocalDate.parse((String) jsonObject.get("endDate"));
        Planner planner = new Planner(projectName, startDate, endDate);

        List<Employee> employeeListTemp = parseEmployeeObject(jsonObject);
        planner.storeEmployees(employeeListTemp);

        List<Milestones> milestoneListTemp = parseMilestoneObject(jsonObject);
        planner.storeMilestones(milestoneListTemp);

        return planner;
    }

    private List<Employee> parseEmployeeObject(JSONObject jsonObject) {

        List<Employee> employeeListTemp = new ArrayList<>();
        JSONArray objArray = (JSONArray) jsonObject.get("employeeList");

        for (int i = 0; i < objArray.size(); i++) {
            JSONObject arrayIn = (JSONObject) objArray.get(i);
            String foreName = (String) arrayIn.get("employeeForename");
            String surName = (String) arrayIn.get("employeeSurname");
            int birthDay = Integer.parseInt((String) arrayIn.get("employeeBirth"));
            double employeeSalary = Double.parseDouble((String) arrayIn.get("salary"));

            Employee employee = new Employee(foreName, surName, birthDay, employeeSalary);
            employeeListTemp.add(employee);
        }
        return employeeListTemp;
    }

    private List<Milestones> parseMilestoneObject(JSONObject jsonObject) {

        List<Milestones> milestoneListTemp = new ArrayList<>();
        JSONArray objArray = (JSONArray) jsonObject.get("milestoneList");

        for (int i = 0; i < objArray.size(); i++) {
            JSONObject arrayIn = (JSONObject) objArray.get(i);
            String taskName = (String) arrayIn.get("taskName");
            LocalDate startDate = LocalDate.parse((String) arrayIn.get("startDate"));
            LocalDate plannedEndDate = LocalDate.parse((String) arrayIn.get("plannedEndDate"));
            LocalDate actualEndDate = LocalDate.parse((String) arrayIn.get("actualEndDate"));
            JSONObject taskMemberMap = (JSONObject) arrayIn.get("hoursPerEmployee");

            Milestones milestone = new Milestones(taskName, startDate, plannedEndDate, actualEndDate, taskMemberMap);
            milestoneListTemp.add(milestone);
        }
        return milestoneListTemp;
    }

}