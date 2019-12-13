package member_manager;

public class Timesheet {
    private int week;
    private int hours;
    //private Milestone milestone;

    public Timesheet(int week, int hours) {
        this.week = week;
        this.hours = hours;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
