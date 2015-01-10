package mike.net.salary.reader;

import mike.net.salary.model.Person;
import mike.net.salary.model.TimeEntry;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static mike.net.salary.util.TestUtil.getPerson;
import static mike.net.salary.util.TestUtil.getTheOneDay;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SalaryCSVReaderTest {

    @Test
    public void testRead() throws Exception {
        final List<Person> entries = SalaryCSVReader.readSalaryCSVFile(new File("HourList201403.csv"));
        assertThat(entries, hasSize(3));
        final Person person = getPerson(2, entries);
        assertThat(person.getId(), is(2L));
        assertThat(person.getName(), equalTo("Scott Scala"));
        assertThat(person.getWorkDays(), hasSize(23));
        List<TimeEntry> timeEntries = getTheOneDay(person, 87);
        assertThat(timeEntries, hasSize(1));
        assertThat(timeEntries.get(0).getStartTime().toString(), equalTo("2014-03-28T08:30"));
        assertThat(timeEntries.get(0).getEndTime().toString(), equalTo("2014-03-28T19:00"));

    }


}