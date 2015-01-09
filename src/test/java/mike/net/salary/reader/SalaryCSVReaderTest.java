package mike.net.salary.reader;

import mike.net.salary.model.Person;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertThat;

public class SalaryCSVReaderTest {

    @Test
    public void testRead() throws Exception {
        final Map<Long, Person> entries = SalaryCSVReader.readSalaryCSVFile(new File("HourList201403.csv"));
        assertThat(entries.keySet(), Matchers.hasSize(3));
    }
}