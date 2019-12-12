package member_manager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Planner {

    private double budget;
    private String projectName;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate localDate = LocalDate.now();  // creates a date based on todays date [Hjalmar]
    private float totalDays = ChronoUnit.DAYS.between(startDate, endDate); // compares 2 dates and returns the diff in numbers [Hjalmar]
    private float nowDays = ChronoUnit.DAYS.between(startDate, localDate);
    private float percentCompletePlanned = nowDays / totalDays;
    private double percentCompleteActual = 0.2; // here we have to sum the time for all tasks, sum all completed tasks and then divide them(?) [Hjalmar]

    ArrayList<Member> members = new ArrayList<>();
    ArrayList<Milestone> milestones = new ArrayList<>();

    public Planner(String projectName, LocalDate startDate, LocalDate endDate, double budget){
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
    }

    public Planner(){}

    /*

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
         double actualCost = 500;  // here we have to sum total cost we spent so far (all workers hours times salary)
         return calcEv() - actualCost;
    }*/

    public void addMember(String firstName, String lastName, int dateOfBirth, double salary){
        Member member = new Member(firstName, lastName, dateOfBirth, salary);
        members.add(member);
    }
    public void printMembers(){
        for(Member member : members){
            member.toString();
        }
    }
    public void storeEmployee(Member employee){
        members.add(employee);
    }
    @Override
    public String toString() {
        return "Name: " + projectName + "\n" +
                "StartDate: " + startDate + "\n" +
                "EndDate: " + endDate + "\n";
    }
}
