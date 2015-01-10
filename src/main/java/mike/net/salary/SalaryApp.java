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
    private static SalaryService salaryService = new SalaryService();

    public static void main(String[] args) throws IOException {
        final List<Person> persons = SalaryCSVReader.readSalaryCSVFile(new File("HourList201403.csv"));
        persons.forEach(person -> {
           salaryService.calculateSalary(person);
        });
    }
}
