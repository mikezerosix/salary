package mike.net.salary.model;

import java.time.LocalDate;
import java.util.*;

public class Person {
    private String name;
    private long id;
    private Map<LocalDate, List<TimeEntry>> workLog = new HashMap<>();

    public Person(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public List<TimeEntry> getWorkLog(LocalDate date) {
        return workLog.get(date);
    }

    public Set<LocalDate> getWorkDays() {
        return workLog.keySet();
    }

    public void addWorkLog(LocalDate date, TimeEntry timeEntry) {
        if (!workLog.containsKey(date)) {
            workLog.put(date, new ArrayList<>());
        }
        workLog.get(date).add(timeEntry);
    }

}
