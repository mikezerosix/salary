package mike.net.salary.reader;

import mike.net.salary.model.Person;
import mike.net.salary.model.TimeEntry;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;

public class SalaryCSVReaderTest {

    @Test
    public void testRead() throws Exception {
        final Map<Long, Person> entries = SalaryCSVReader.read(new File("HourList201403.csv"));
        assertThat(entries.keySet(), Matchers.hasSize(3));
    }
}