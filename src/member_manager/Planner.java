package member_manager;

import json_reader_writer.JsonReader;
import json_reader_writer.JsonWriter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    private double percentCompleteActual = 0.2; // This need to be a method which collects the data from the milestones.

    public ArrayList<Member> members = loadMember();
    public ArrayList<Milestone> milestones = loadMilestones();
    public ArrayList<RiskMatrix> risks = loadRiskMatrix();
    public ArrayList<Timesheet> timesheets = loadTimesheet();
    public ArrayList<Float> pcpValues = new ArrayList<>();
    public ArrayList<Double> actualCosts = new ArrayList<>();


    public Planner(String projectName, LocalDate startDate, LocalDate endDate, double budget) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        this.nowDays = ChronoUnit.DAYS.between(startDate, localDate);
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
        int totalWeeks = (int) totalDays/7;
        for (int i = 0; i < totalWeeks; i++) {
            float pcp = (float) (Math.round((budget/(totalWeeks-i))*100.00)/100.00);
            pcpValues.add(pcp);
        }
        return pcpValues;
    }

    public double calcEv(){
         return percentCompleteActual * budget;
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

    public ArrayList<Double> calcActualCost() { //Shows actual cost per milestone instead of per week since we don't have enough milestones in input.json atm
        double costsPerMilestone = 0;
        double hoursPerMilestone = 0;

        for (Milestone milestone : milestones) {
            for (Member member : members) {
                hoursPerMilestone = hoursPerMilestone + (double) milestone.getEmployeeHours().get(member.getId());
                costsPerMilestone = costsPerMilestone + (member.getSalary() * hoursPerMilestone);
            }
            actualCosts.add(costsPerMilestone);
            hoursPerMilestone = 0;
            costsPerMilestone = 0;
        }
        System.out.println(actualCosts);
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

    public ArrayList<Timesheet> loadTimesheet() {
        return reader.loadTimesheet();
    };

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
