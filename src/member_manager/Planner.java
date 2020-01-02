package member_manager;

import json_reader_writer.JsonReader;
import json_reader_writer.JsonWriter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Planner {

    JsonReader reader = new JsonReader();

    private double budget;
    private String projectName;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate localDate = LocalDate.now();  // creates a date based on todays date [Hjalmar]
    private float totalDays;
    private float nowDays;
    //private float percentCompletePlanned = nowDays / totalDays; NOTICE: MOVED CALCULATIONS INTO calcPv();
    //private double percentCompleteActual = 0.85; // Changed so percent complete is decided every week in input.json

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
        //System.out.println("All hours from certain week: " + totalHours); //debugger
        return totalHours;
    }

    public double getAllHoursForMember(int searchID) { //Can be used in place of get hours from milestone
        double totalHours = 0;
        for (Integer week : timesheet.keySet()) {
            Map<Integer, Double> hoursMap = timesheet.get(week);
            for (Integer memberID : hoursMap.keySet()) {
                if (searchID == memberID) {
                    totalHours = totalHours + (Double.parseDouble(hoursMap.get(memberID).toString()));
                }
            }
        }
        //System.out.println("All hours from particular member: " + totalHours); //debugger
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
        //System.out.println("Hours for a particular member for a specified week: " + totalHours); //debugger
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
        //System.out.println("Weekly salary for a specified week for a particular member: " + totalWeeklyMemberSalary); //debugger
        return totalWeeklyMemberSalary;
    }

    public double getTotalSalaryForMember(int searchID) {
        double totalMemberSalary = 0;
        for (Integer week : timesheet.keySet()) {
            double weeklySalary = getWeeklySalaryForMember(week, searchID);
            totalMemberSalary = totalMemberSalary + weeklySalary;
        }
        //System.out.println("Salary for total hours worked by a particular member: " + totalMemberSalary); //debugger
        return totalMemberSalary;
    }

    //searches for a specific member and returns sum of total hours for all milestones
    public double getTotalHours(int ID) {
        double result = 0;
        for (Milestone milestone : milestones) {
            if (milestone.getEmployeeHours().containsKey(ID)) {
                result =  result + ((double) milestone.getEmployeeHours().get(ID));
            }
        }
        return result;
    }
    //searches for a specific milestone and member and returns hours for that milestone (can be reused to we can check all members hours for a specific milestone)
    public ArrayList<Double> getHours(int ID){
        ArrayList<Double> result = new ArrayList<>();
        for (Milestone milestone : milestones){
            result.add(((double) milestone.getEmployeeHours().get(ID)));
        }
        //System.out.println(result); debug
        return result;
    }

    public int calcWeek() {
        int week = ((int) (nowDays/7)) + 1; //+1 is used to move the current week to the week we're currently on instead of weeks that have passed.
        return week;
    }

    public ArrayList<Float> calcPv(){
        ArrayList<Float> pcpValues = new ArrayList<>();
        int totalWeeks = (int) totalDays/7;
        for (int i = 0; i < totalWeeks; i++) {
            float pcp = (float) (Math.round((budget/(totalWeeks-i))*100.00)/100.00);
            pcpValues.add(pcp);
        }
        return pcpValues;
    }

    public ArrayList<Double> calcEv(){
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < percentComplete.size(); i++) {
            result.add((double) Math.round(((percentComplete.get(i) * budget)*100.00)/100.00));
        }
        System.out.println(result); //debugger
        return result;
    }

    /*
    public double calcSv(){
         return calcEv() - calcPv();
    }

     */

    /*
    public double calcCv(){
         return calcEv() - calcActualCost();
    }
     */

    public ArrayList<Double> calcActualCost() {
        ArrayList<Double> actualCosts = new ArrayList<>();
        for (Member member : members) {
            actualCosts.add(getTotalSalaryForMember(member.getId()));
        }
        System.out.println(actualCosts); //debugger
        return actualCosts;
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
    //removed printMembers method as ArrayList has one implemented.

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
