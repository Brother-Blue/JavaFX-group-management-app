package member_manager;

import java.time.LocalDate;
import java.util.ArrayList;

public class Milestone {
    private String milestoneName;
    private boolean accomplished;
    private String milestoneDescription;
    LocalDate startDate;
    LocalDate endDate;
    private int memberID;
    ArrayList<Member> contribution;

    public Milestone(String milestoneName, LocalDate startDate, LocalDate endDate, String milestoneDescription, ArrayList<Member> contribution, boolean accomplished) {
        this.milestoneName = milestoneName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.milestoneDescription = milestoneDescription;
        this.accomplished = accomplished;
        this.contribution = contribution;

    }

    public void setHours(){

    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public boolean isAccomplished() {
        return accomplished;
    }

    public String getMilestoneDescription() {
        return milestoneDescription;
    }

    @Override
    public String toString() {
        return "{" +
                "Milestone : '" + milestoneName + '\'' +
                ", Is it accomplished : " + accomplished +
                ", Milestone Description : '" + milestoneDescription + '\'' +
                ", Accomplished by ID : " + memberID +
                '}';
    }
}
