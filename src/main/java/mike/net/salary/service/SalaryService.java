package mike.net.salary.service;

import mike.net.salary.calculator.SalaryBuilder;
import mike.net.salary.calculator.WorkTimeCalculator;
import mike.net.salary.model.Person;
import mike.net.salary.model.Salary;
import mike.net.salary.model.SalaryRate;
import mike.net.salary.writer.ConsoleWriter;

public class SalaryService {
    private static final double BASE_RATE = 3.75 / 60;
    private static final double IRREGULAR_RATE = 1.15 / 60;
    private static final WorkTimeCalculator workTimeCalculator = new WorkTimeCalculator();
    private static final ConsoleWriter writer = new ConsoleWriter();

    public SalaryRate getSalaryRate() {
        return new SalaryRate(BASE_RATE, IRREGULAR_RATE, BASE_RATE * 0.25, BASE_RATE * 0.5, BASE_RATE);
    }

    private Salary getSalary(Person person) {
        SalaryBuilder salaryBuilder = new SalaryBuilder();
        salaryBuilder.setId(person.getId());
        salaryBuilder.setName(person.getName());
        salaryBuilder.setSalaryRate(getSalaryRate());
        salaryBuilder.setWorkTime(workTimeCalculator.calculateWorkTime(person));
        return salaryBuilder.build();
    }
    public void calculateSalary(Person person) {
        final Salary salary = getSalary(person);
        writer.writeSalary(salary);
    }

}
