package member_manager;

public class Milestone {
    private String milestoneName;
    private boolean accomplished;
    private String milestoneDescription;
    private int memberID;

    public Milestone(String milestoneName, String milestoneDescription, Member member ,boolean accomplished) {
        this.milestoneName = milestoneName;
        this.milestoneDescription = milestoneDescription;
        this.accomplished = accomplished;
        this.memberID = member.getId(); //Requires member to be initialized in order to get member's ID.
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
