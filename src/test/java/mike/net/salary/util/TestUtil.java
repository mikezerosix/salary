package mike.net.salary.util;

import mike.net.salary.model.Person;
import mike.net.salary.model.TimeEntry;

import java.time.LocalDate;
import java.util.List;

public class TestUtil {

    public static Person getPerson(long id, List<Person> entries) {
        for (Person person : entries) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public static List<TimeEntry> getTheOneDay(Person person, int doy) {
        for (LocalDate localDate : person.getWorkDays()) {
            if (localDate.getDayOfYear() == doy) {
                return person.getWorkLog(localDate);
            }
        }
        return null;
    }
}
