package member_manager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Timesheet {
    private Planner planner;
    private LocalDate startDate = planner.getStartDate();
    private LocalDate endDate = planner.getEndDate();
    private ArrayList<Integer> memberIDs = getMemberIDs();
    private ArrayList<Integer> weekCount = initWeekCount();
    private Map<Integer, ArrayList<Integer>> weeks = initTimeSheet();

    private Map<Integer, Integer> getMemberHours(int week, int memberID) {
        Map<Integer, Integer> weeklyHours = new HashMap<>();
        if (weeks.containsKey(week) && containsID(memberID)) {
            //stuff
        }
        return weeklyHours;
    }

    private Map<Integer, ArrayList<Integer>> initTimeSheet() {
        Map<Integer, ArrayList<Integer>> timesheet = new HashMap<>();
        for (Integer week : weekCount) {
            timesheet.put(week, memberIDs);
        }
        return timesheet;
    }

    private ArrayList<Integer> initWeekCount() {
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        int dayCount = 0;
        ArrayList<Integer> weekCount = new ArrayList<>();
        for (int i = 0; i < days; i+=7) {
            dayCount++;
            weekCount.add(dayCount);
        }
        return weekCount;
    }

    private ArrayList<Integer> getMemberIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Member member : planner.members) {
            ids.add(member.getId());
        }
        return ids;
    }

    private boolean containsID(int ID) {
        boolean result = false;
        for (Member member : planner.members) {
            if (member.getId() == ID) {
                result = true;
                break;
            }
        }
        return result;
    }
}
