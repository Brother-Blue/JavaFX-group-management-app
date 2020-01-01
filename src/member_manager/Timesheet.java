package member_manager;

import java.util.ArrayList;
import java.util.Map;

public class Timesheet {
    private Map<Integer, ArrayList<Double>> weeks;

    public Timesheet(Map<Integer, ArrayList<Double>> weeks) {
        this.weeks = weeks;
    }
}
