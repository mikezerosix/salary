package mike.net.salary;

import mike.net.salary.model.Person;
import mike.net.salary.model.Salary;
import mike.net.salary.reader.SalaryCSVReader;
import mike.net.salary.service.SalaryService;
import mike.net.salary.writer.ConsoleWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class SalaryApp {
    public static final String HOUR_LIST = "HourList201403.csv";
    private static SalaryService salaryService = new SalaryService();

    public static void main(String[] args) throws IOException {
        System.out.printf(" Wages %s/%s\n", HOUR_LIST.substring(8,12), HOUR_LIST.substring(12,14));
        final List<Person> persons = SalaryCSVReader.readSalaryCSVFile(new File(HOUR_LIST));
        persons.forEach(person -> {
            salaryService.calculateSalary(person);
        });
    }
}
