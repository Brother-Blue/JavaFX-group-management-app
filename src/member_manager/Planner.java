package member_manager;

import json_reader_writer.JsonReader;
import json_reader_writer.JsonWriter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Planner {

    // Hello gamers

    JsonReader reader = new JsonReader();

    private double budget;
    private String projectName;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate localDate = LocalDate.now();
    private float totalDays;
    private float nowDays;

    public ArrayList<Member> members = loadMember();
    public ArrayList<Milestone> milestones = loadMilestones();
    public ArrayList<RiskMatrix> risks = loadRiskMatrix();
    public Map<Integer, Map<Integer, Double>> timesheet = loadTimesheet();
    public ArrayList<Double> percentComplete = loadPercentComplete();


    public Planner(String projectName, LocalDate startDate, LocalDate endDate, double budget) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        this.nowDays = ChronoUnit.DAYS.between(startDate, localDate);
    }


    public double getAllHoursFromWeek(int week) { //Can be used in place of get hours from milestone
        double totalHours = 0;
        if (timesheet.containsKey(week)) {
            Map<Integer, Double> hoursMap = timesheet.get(week);
            for (Double value : hoursMap.values()) {
                totalHours = totalHours + value;
            }
        }
        return totalHours;
    }

    public double getAllHoursForMember(int searchID) {
        double totalHours = 0;
        for (Integer week : timesheet.keySet()) {
            Map<Integer, Double> hoursMap = timesheet.get(week);
            for (Integer memberID : hoursMap.keySet()) {
                if (searchID == memberID) {
                    totalHours = totalHours + (Double.parseDouble(hoursMap.get(memberID).toString()));
                }
            }
        }
        return totalHours;
    }

    public double getWeeklyHoursForMember(int searchWeek, int searchID) {
        double totalHours = 0;
        if (timesheet.containsKey(searchWeek)) {
            Map<Integer, Double> week = timesheet.get(searchWeek);
            if (week.containsKey(searchID)) {
                totalHours = week.get(searchID);
            }
        }
        return totalHours;
    }

    public double getWeeklySalaryForMember(int searchWeek, int searchID) {
        boolean memberFound = false;
        double memberSalary = 0;
        double totalWeeklyMemberSalary = 0;
        for (Member member : members) {
            if (searchID == member.getId()) {
                memberFound = true;
                memberSalary = member.getSalary();
            }
        }
        if (memberFound) {
            double hoursWorked = getWeeklyHoursForMember(searchWeek, searchID);
            totalWeeklyMemberSalary = hoursWorked * memberSalary;
        }
        return totalWeeklyMemberSalary;
    }

    public double getTotalSalaryForMember(int searchID) {
        double totalMemberSalary = 0;
        for (Integer week : timesheet.keySet()) {
            double weeklySalary = getWeeklySalaryForMember(week, searchID);
            totalMemberSalary = totalMemberSalary + weeklySalary;
        }
        return totalMemberSalary;
    }

    public ArrayList<String> getMilestonesWorkedOn(int id) {
        ArrayList<String> milestonesWorkedOn = new ArrayList<>();
        for (Milestone milestone : milestones) {
            Map<Integer, Double> employeeHours = milestone.getEmployeeHours();
            if (employeeHours.containsKey(id) && (employeeHours.get(id) > 0)) {
                milestonesWorkedOn.add(milestone.getMilestoneName());
            }
        }
        return milestonesWorkedOn;
    }


    public int calcWeek() {
        int week = ((int) (nowDays/7)) + 1; //+1 is used to move the current week to the week we're currently on instead of weeks that have passed.
        return week;
    }

    public ArrayList<Double> calcPv(){
        ArrayList<Double> pcpValues = new ArrayList<>();
        int totalWeeks = (int) totalDays/7;
        for (int i = 0; i < totalWeeks; i++) {
            double pcp = (Math.round((budget/(totalWeeks-i))*100.00)/100.00);
            pcpValues.add(pcp);
        }
        return pcpValues;
    }

    public ArrayList<Double> calcEv(){
        ArrayList<Double> results = new ArrayList<>();
        for (int i = 0; i < percentComplete.size(); i++) {
            results.add((double) Math.round(((percentComplete.get(i) * budget)*100.00)/100.00));
        }
        return results;
    }

    public ArrayList<Double> calcSv(){ //ev-pv
        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Double> evResults = calcEv();
        ArrayList<Double> pvResults = calcPv();

        for (int i = 0; i < pvResults.size(); i++) {
            double result = (Math.round(evResults.get(i) - pvResults.get(i))*100.00/100.00);
            results.add(result);
        }
        return results;
    }

    public ArrayList<Double> calcCv(){
        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Double> avResults = calcActualCost();
        ArrayList<Double> evResults = calcEv();

        for (int i = 0; i < avResults.size(); i++) {
            double result = (Math.round(evResults.get(i) - avResults.get(i)*100.00)/100.00);
            results.add(result);
        }
        return results;
    }

    public ArrayList<Double> calcActualCost() {
        ArrayList<Double> actualCosts = new ArrayList<>();
        double weeklyCosts = 0;

        for (int i = 0; i < calcWeek(); i++) {
            for (Member member : members) {
                weeklyCosts = getWeeklySalaryForMember(i, member.getId());
            }
            actualCosts.add(weeklyCosts);
            weeklyCosts = 0;
        }
        return actualCosts;
    }

    public ArrayList<Double> calcSPI() {
        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Double> evResults = calcEv();
        ArrayList<Double> pvResults = calcPv();

        for (int i = 0; i < pvResults.size(); i++) {
            double result = (evResults.get(i)/pvResults.get(i));
            results.add(result);
        }
        return results;
    }

    public ArrayList<Double> calcCPI() { //Values displayed are skewed due to not all hours input into the json file.
        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Double> evResults = calcEv();
        ArrayList<Double> acResults = calcActualCost();
        double acResult = 0;
        double evResult = 0;
        double maxAcResult = Collections.max(acResults);
        double maxEvResult = Collections.max(evResults);

        for (int i = 0; i < acResults.size(); i++) {
            if (acResults.get(i) == 0) {
                acResult = maxEvResult;
            } else {
                acResult = acResults.get(i);
            }
            if (evResults.get(i) == 0) {
                evResult = maxAcResult;
            } else {
                evResult = evResults.get(i);
            }

            double result = (evResult/acResult);
            results.add(result);
        }
        return results;
    }

    public ArrayList<Double> calcBAC() {
        double totalSalaries = 0;
        ArrayList<Double> results = new ArrayList<>();
        for (int i = 0; i < calcWeek(); i++) {
            System.out.println(i);
            for (Member member : members) {
                totalSalaries = totalSalaries + getWeeklySalaryForMember(i+1, member.getId());
            }
            double result = budget - totalSalaries;
            results.add(result);
        }

        return results;
    }

    //method for creating a new member
    public void addMember(String firstName, String lastName, int dateOfBirth, double salary) {
        Member member = new Member(firstName, lastName, dateOfBirth, salary);
        members.add(member);
    }
    //method for creating a new milestone
    public void addMilestone(String milestoneName, String milestoneDescription, LocalDate startDate, LocalDate actualEndDate, LocalDate plannedEndDate, Map<Integer, Double> employeeHours) {
        Milestone milestone = new Milestone(milestoneName, milestoneDescription, startDate, actualEndDate, plannedEndDate , employeeHours);
        milestones.add(milestone);
    }

    public void addRisks(String riskName, String veryLikely, String possible, String unlikely){
        RiskMatrix risk = new RiskMatrix(riskName, veryLikely, possible, unlikely);
        risks.add(risk);
    }


    public ArrayList<Member> loadMember(){
        return reader.loadMember();
    }

    public ArrayList<Milestone> loadMilestones(){
        return reader.loadMilestone();
    }

    public ArrayList<RiskMatrix> loadRiskMatrix(){
        return reader.loadRiskMatrix();
    }

    public Map<Integer, Map<Integer, Double>> loadTimesheet() {
        return reader.loadTimesheet();
    };

    public ArrayList<Double> loadPercentComplete() {
        return reader.percentComplete();
    }

    public double getBudget() {
        return budget;
    }

    public String getProjectName() {
        return projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Milestone> getMilestones() {
        return milestones;
    }

    public ArrayList<RiskMatrix> getRisks() {
        return risks;
    }

    @Override
    public String toString() {
        return "Name: " + projectName + "\n" +
                "StartDate: " + startDate + "\n" +
                "EndDate: " + endDate + "\n";
    }
}
