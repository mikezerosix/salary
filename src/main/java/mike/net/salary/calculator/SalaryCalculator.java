package mike.net.salary.calculator;

import mike.net.salary.model.Person;
import mike.net.salary.model.Salary;
import mike.net.salary.model.TimeEntry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class SalaryCalculator {
    public static final int TWO_HOURS_IN_MINUTES = 2 * 60;
    public static final int OVERTIME_LIMIT = 8 * 60;
    private double minuteRate;
    private static final LocalTime EVENING_TIME_START = LocalTime.of(18, 00);
    private static final LocalTime EVENING_TIME_END = LocalTime.of(6, 00);

    public SalaryCalculator(double hourlyRate) {
        this.minuteRate = hourlyRate / 60;
    }

    public Salary calculateSalary(Person person) {
        Salary salary = new Salary();
        person.getWorkDays().forEach(workday -> {
            long baseMinutes = 0;
            long irregularMinutes = 0;
            for (TimeEntry timeEntry : person.getWorkLog(workday)) {
                baseMinutes += timeEntryBaseMinutes(timeEntry);
                irregularMinutes += timeEntryIrregularMinutes(timeEntry);
            }
            addDayToSalary(salary, baseMinutes, irregularMinutes);
        });
        return salary;
    }

    protected void addDayToSalary(Salary salary, long baseMinutes, long eveningMinutes) {
        salary.base += baseMinutes;
        salary.irregular += eveningMinutes;

        long overtime = baseMinutes - OVERTIME_LIMIT;

        if (overtime <= 0) {
            return;
        }
        if (overtime <= TWO_HOURS_IN_MINUTES) {
            salary.overtimeQuarter += overtime;
            return;
        }

        salary.overtimeQuarter += TWO_HOURS_IN_MINUTES;
        overtime -= TWO_HOURS_IN_MINUTES;

        if (overtime <= TWO_HOURS_IN_MINUTES) {
            salary.overtimeHalf += overtime;
            return;
        }
        salary.overtimeHalf += TWO_HOURS_IN_MINUTES;
        overtime -= TWO_HOURS_IN_MINUTES;
        salary.overtimeFull += overtime;
    }

    protected long timeEntryBaseMinutes(TimeEntry timeEntry) {
        Duration duration = Duration.between(timeEntry.getStartTime(), timeEntry.getEndTime());
        return duration.toMinutes();
    }

    protected long timeEntryIrregularMinutes(TimeEntry timeEntry) {
        if (timeEntry.overnight()) {
            TimeEntry tail = new TimeEntry();
            tail.setStartTime(LocalDateTime.of(timeEntry.getEndTime().toLocalDate(), LocalTime.MIN));
            tail.setEndTime(timeEntry.getEndTime());
            TimeEntry head = new TimeEntry();
            head.setStartTime(timeEntry.getStartTime());
            head.setEndTime(LocalDateTime.of(timeEntry.getEndTime().toLocalDate(), LocalTime.MAX));
            return intersection(tail, LocalTime.MIN, EVENING_TIME_START) + intersection(head, EVENING_TIME_END, LocalTime.MAX);
        }
        return intersection(timeEntry, LocalTime.MIN, EVENING_TIME_START) + intersection(timeEntry, EVENING_TIME_END, LocalTime.MAX);
    }

    private long intersection(TimeEntry timeEntry, LocalTime start, LocalTime end) {
        if (timeEntry.getStartTime().toLocalTime().isBefore(end)) {
            start = timeEntry.getStartTime().toLocalTime();
        }
        if (timeEntry.getEndTime().toLocalTime().isBefore(end)) {
            end = timeEntry.getEndTime().toLocalTime();
        }
        final Duration between = Duration.between(start, end);
        if (between.isNegative()) {
            return 0;
        }
        return between.toMinutes();
    }
}
