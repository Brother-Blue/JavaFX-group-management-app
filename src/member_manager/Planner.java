package member_manager;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Planner {

    private double budget;
    private String projectName;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate localDate = LocalDate.now();  // creates a date based on todays date [Hjalmar]
    private float totalDays;
    private float nowDays;
    private float percentCompletePlanned = nowDays / totalDays;
    private double percentCompleteActual = 0.2; // This need to be a method which collects the data from the milestones.

    public ArrayList<Member> members = new ArrayList<>();
    public ArrayList<Milestone> milestones = new ArrayList<>();

    public Planner() {
        //Needed to reference the addMember method
    }

    public Planner(String projectName, LocalDate startDate, LocalDate endDate, double budget){
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        this.nowDays = ChronoUnit.DAYS.between(startDate, localDate);
    }

    public double calcPv(){
         return percentCompletePlanned * budget;
    }

    public double calcEv(){
         return percentCompleteActual * budget;
    }

    public double calcSv(){
         return calcEv() - calcPv();
    }

    public double calcCv(){
         return calcEv() - calcActualCost();
    }

    //TODO We need to find a way to extract hours spent on milestones depending on ID in order to do the calculation.
    public double calcActualCost(){
        double actualCost = 0;
        for(Member member:members){
            member.getSalary();
            for(Milestone milestone : milestones){

            }
        }
        return actualCost;
    }

    public void addMember(String firstName, String lastName, int dateOfBirth, double salary){
        Member member = new Member(firstName, lastName, dateOfBirth, salary);
        members.add(member);
    }

    public void addMilestone(String milestoneName, String milestoneDescription, Member member ,boolean accomplished){
        Milestone milestone = new Milestone(milestoneName, milestoneDescription, member , accomplished);
        milestones.add(milestone);
    }

    //removed printMembers method as ArrayList has one implemented.

    @Override
    public String toString() {
        return "Name: " + projectName + "\n" +
                "StartDate: " + startDate + "\n" +
                "EndDate: " + endDate + "\n";
    }
}
