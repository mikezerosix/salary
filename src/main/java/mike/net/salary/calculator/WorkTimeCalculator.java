package mike.net.salary.calculator;

import mike.net.salary.model.Person;
import mike.net.salary.model.WorkTime;
import mike.net.salary.model.TimeEntry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class WorkTimeCalculator {
    public static final int OVERTIME_MULTIPLIER_LIMIT = 2 * 60;
    public static final int OVERTIME_LIMIT = 8 * 60;
    public static final LocalTime IRREGULAR_ZONE_END = LocalTime.NOON;
    public static final int IRREGULAR_HOURS = 12;

    public WorkTime calculateWorkTime(Person person) {
        WorkTime workTime = new WorkTime();
        person.getWorkDays().forEach(workday -> {
            long baseMinutes = 0;
            for (TimeEntry timeEntry : person.getWorkLog(workday)) {
                baseMinutes += timeEntryBaseMinutes(timeEntry);
                workTime.addToIrregular(timeEntryIrregularMinutes(timeEntry));
            }
            workTime.addToBase(baseMinutes);
            addOvertime(workTime, baseMinutes);
        });
        return workTime;
    }

    protected void addOvertime(WorkTime workTime, long baseMinutes) {
        long overtime = baseMinutes - OVERTIME_LIMIT;
        if (overtime <= 0) {
            return;
        }
        if (overtime <= OVERTIME_MULTIPLIER_LIMIT) {
            workTime.addToOvertimeQuarter(overtime);
            return;
        }

        workTime.addToOvertimeQuarter(OVERTIME_MULTIPLIER_LIMIT);
        overtime -= OVERTIME_MULTIPLIER_LIMIT;

        if (overtime <= OVERTIME_MULTIPLIER_LIMIT) {
            workTime.addToOvertimeHalf(overtime);
            return;
        }
        workTime.addToOvertimeHalf(OVERTIME_MULTIPLIER_LIMIT);
        overtime -= OVERTIME_MULTIPLIER_LIMIT;
        workTime.addToOvertimeFull(overtime);
    }

    protected long timeEntryBaseMinutes(TimeEntry timeEntry) {
        Duration duration = Duration.between(timeEntry.getStartTime(), timeEntry.getEndTime());
        return duration.toMinutes();
    }

    protected long timeEntryIrregularMinutes(TimeEntry timeEntry) {
        LocalTime start = offsetTimeToIrregularZone(timeEntry.getStartTime());
        LocalTime end = offsetTimeToIrregularZone(timeEntry.getEndTime());

        Duration duration;
        if (start.isBefore(IRREGULAR_ZONE_END)) {
            if (end.isAfter(IRREGULAR_ZONE_END)) {
                end = IRREGULAR_ZONE_END;
            }
            duration = Duration.between(start, end);

            if (duration.isNegative()) {
                duration = duration.plusHours(IRREGULAR_HOURS);
            }
            return duration.toMinutes();

        } else if (end.isBefore(IRREGULAR_ZONE_END)) {
            return Duration.between(LocalTime.MIN, end).toMinutes();
        }

        return 0;
     }

    /** Offset so that we do not have to deal with irregular Time Zone (18-06) spanning the day limit
     */
    private LocalTime offsetTimeToIrregularZone(LocalDateTime time) {
        return time.toLocalTime().minusHours(18);
    }
}
