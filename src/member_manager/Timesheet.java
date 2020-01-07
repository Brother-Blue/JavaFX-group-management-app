package member_manager;

import java.util.ArrayList;
import java.util.Map;

public class Timesheet {
    private Map<Integer, ArrayList<Double>> weeks; //dont touch

    public Timesheet(Map<Integer, ArrayList<Double>> weeks) {
        this.weeks = weeks;
    }
}
