package mike.net.salary.calculator;

import mike.net.salary.model.TimeEntry;
import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SalaryCalculatorTest {
    final SalaryCalculator salaryCalculator = new SalaryCalculator(3.75);

    @Test
    public void testCalculateBaseSalary() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalTime.of(6, 00));
        timeEntry.setEndTime(LocalTime.of(14, 00));
       /* final double salary = salaryCalculator.calculateBaseSalary(timeEntry);
        assertThat(salary, equalTo(300));
    */
    }

    @Test
    public void testTimeEntryBaseMinutes() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalTime.of(6, 00));
        timeEntry.setEndTime(LocalTime.of(14, 00));
        assertThat(salaryCalculator.timeEntryBaseMinutes(timeEntry), equalTo(480L));
    }

    @Test
    public void testTimeEntryEveningMinutesMorning() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalTime.of(5, 00));
        timeEntry.setEndTime(LocalTime.of(14, 00));
        assertThat(salaryCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(60L));
    }

    @Test
    public void testTimeEntryEveningMinutesNone() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalTime.of(6, 00));
        timeEntry.setEndTime(LocalTime.of(14, 00));
        assertThat(salaryCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(0L));
    }

    @Test
    public void testTimeEntryEveningMinutesNotNegative() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalTime.of(7, 00));
        timeEntry.setEndTime(LocalTime.of(14, 00));
        assertThat(salaryCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(0L));
    }

    @Test
    public void testTimeEntryEveningMinutesEvening() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalTime.of(7, 00));
        timeEntry.setEndTime(LocalTime.of(19, 00));
        assertThat(salaryCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(60L));
    }

    @Test
    public void testTimeEntryEveningMinutesNone2() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalTime.of(6, 00));
        timeEntry.setEndTime(LocalTime.of(18, 00));
        assertThat(salaryCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(0L));
    }
    @Test
    public void testTimeEntryEveningMinutesBoth() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalTime.of(5, 00));
        timeEntry.setEndTime(LocalTime.of(19, 00));
        assertThat(salaryCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(120L));
    }


    @Test
    public void testAddDayToSalary() throws Exception {


    }
}