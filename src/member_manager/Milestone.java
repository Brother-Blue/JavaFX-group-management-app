package member_manager;

import java.time.LocalDate;
import java.util.Map;

public class Milestone {
    private String milestoneName;
    private String milestoneDescription;
    private LocalDate startDate;
    private LocalDate plannedEndDate;
    private LocalDate actualEndDate;

    private Map<Integer, Double> employeeHours; //ID as the key, amount of hour as the value. ex. ID 1, hours 8

    public Milestone(String milestoneName, String milestoneDescription, LocalDate startDate, LocalDate plannedEndDate, LocalDate actualEndDate , Map <Integer, Double> employeeHours) {
        this.milestoneName = milestoneName;
        this.milestoneDescription = milestoneDescription;
        this.startDate = startDate;
        this.plannedEndDate = plannedEndDate;
        this.actualEndDate = actualEndDate;
        this.employeeHours = employeeHours;
    }

    public String getMilestoneName() {
        return milestoneName;
    }


    public Map getEmployeeHours() {
        return employeeHours;
    }

    public boolean isAccomplished(LocalDate today) {
        if(actualEndDate.isBefore(today)){
            return true;
        }
        return false;
    }

    public String getMilestoneDescription() {
        return milestoneDescription;
    }

    @Override
    public String toString() {
        return "{" +
                "Milestone : '" + milestoneName + '\'' +
                ", Milestone Description : '" + milestoneDescription + '\'' +
                ", Accomplished by ID : " + employeeHours +
                '}';
    }
}
