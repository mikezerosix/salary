package mike.net.salary.integration;

import mike.net.salary.calculator.WorkTimeCalculator;
import mike.net.salary.model.Person;
import mike.net.salary.model.WorkTime;
import mike.net.salary.reader.SalaryCSVReader;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static mike.net.salary.util.TestUtil.getPerson;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReaderWorkTimeTest {
    private static final WorkTimeCalculator workTimeCalculator = new WorkTimeCalculator();

    @Test
    public void testPerson1() throws Exception {
        final List<Person> entries = SalaryCSVReader.readSalaryCSVFile(new File("HourList201403.csv"));
        final Person person = getPerson(1, entries);
        final WorkTime workTime = workTimeCalculator.calculateWorkTime(person);
        assertThat(workTime.getBase(), is(173L*60));

    }
}
