package mike.net.salary.reader;

import mike.net.salary.model.Person;
import mike.net.salary.model.TimeEntry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryCSVReader {

    public static final String PERSON_NAME = "Person Name";
    public static final String PERSON_ID = "Person ID";
    public static final String DATE = "Date";
    public static final String START = "Start";
    public static final String END = "End";
    public static final CSVFormat SALARY_CSV_FORMAT = CSVFormat.RFC4180.withHeader(PERSON_NAME, PERSON_ID, DATE, START, END).withSkipHeaderRecord(true);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.M.yyyy");
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");

    public static Map<Long, Person> read(File file) throws IOException {
        Map<Long, Person> personMap = new HashMap<>();
        final List<CSVRecord> records = getParseCSVRecords(file);
        records.forEach(record -> {
            final Person person = getPerson(personMap, record);
            person.addWorkLog(LocalDate.parse(record.get(DATE), DATE_FORMAT), parseTimeEntry(record));
        });
        return personMap;
    }

    private static Person getPerson(Map<Long, Person> personMap, CSVRecord record) {
        final Long id = Long.valueOf(record.get(PERSON_ID));
        if (!personMap.containsKey(id)) {
            personMap.put(id, new Person(record.get(PERSON_NAME), id));
        }
        return personMap.get(id);
    }

    private static List<CSVRecord> getParseCSVRecords(File file) throws IOException {
        final CSVParser parser = CSVParser.parse(file, Charset.forName("UTF-8"), SALARY_CSV_FORMAT);
        return parser.getRecords();
    }

    private static TimeEntry parseTimeEntry(CSVRecord record) {
        final TimeEntry timeEntry = new TimeEntry();
        LocalDate day = LocalDate.parse(record.get(DATE));
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.parse(record.get(START), TIME_FORMAT)));
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.parse(record.get(END), TIME_FORMAT)));
        return timeEntry;
    }

}
