package mike.net.salary.calculator;

import mike.net.salary.model.TimeEntry;
import mike.net.salary.model.WorkTime;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WorkTimeCalculatorTest {
    final WorkTimeCalculator workTimeCalculator = new WorkTimeCalculator();
    public static final LocalDate day = LocalDate.now();

    @Test
    public void testTimeEntryBaseMinutes() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(6, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day, LocalTime.of(14, 00)));
        assertThat(workTimeCalculator.timeEntryBaseMinutes(timeEntry), equalTo(480L));
    }

    @Test
    public void testTimeEntryIrregularMinutesMorning() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(5, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day, LocalTime.of(14, 00)));
        assertThat(workTimeCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(60L));
    }

    @Test
    public void testTimeEntryIrregularMinutesNone() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(6, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day, LocalTime.of(14, 00)));
        assertThat(workTimeCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(0L));
    }

    @Test
    public void testTimeEntryIrregularMinutesNotNegative() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(7, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day, LocalTime.of(14, 00)));
        assertThat(workTimeCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(0L));
    }

    @Test
    public void testTimeEntryIrregularMinutesIrregular() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(7, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day, LocalTime.of(19, 00)));
        assertThat(workTimeCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(60L));
    }

    @Test
    public void testTimeEntryIrregularMinutesNone2() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(6, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day, LocalTime.of(18, 00)));
        assertThat(workTimeCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(0L));
    }

    @Test
    public void testTimeEntryIrregularMinutesBoth() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(5, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day, LocalTime.of(19, 00)));
        assertThat(workTimeCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(120L));
    }

    @Test
    public void testTimeEntryIrregularMinutesOvernightLong() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(5, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day.plusDays(1), LocalTime.of(4, 30)));
        assertThat(workTimeCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(690L));
    }

    @Test
    public void testTimeEntryIrregularMinutesOvernight() throws Exception {
        final TimeEntry timeEntry = new TimeEntry();
        timeEntry.setStartTime(LocalDateTime.of(day, LocalTime.of(7, 00)));
        timeEntry.setEndTime(LocalDateTime.of(day.plusDays(1), LocalTime.of(4, 30)));
        assertThat(workTimeCalculator.timeEntryIrregularMinutes(timeEntry), equalTo(630L));
    }

    @Test
    public void testAddOvertimeNone() throws Exception {
        WorkTime workTime = new WorkTime();
        workTimeCalculator.addOvertime(workTime, 60 * 8);
        assertThat(workTime.getOvertimeQuarter(), is(0L));
    }

    @Test
    public void testAddOvertimeLessThanNone() throws Exception {
        WorkTime workTime = new WorkTime();
        workTimeCalculator.addOvertime(workTime, 60 * 5);
        assertThat(workTime.getOvertimeQuarter(), is(0L));
    }

    @Test
    public void testAddOvertimeHalfHour() throws Exception {
        WorkTime workTime = new WorkTime();
        workTimeCalculator.addOvertime(workTime, 60 * 8 + 30);
        assertThat(workTime.getOvertimeQuarter(), is(30L));
        assertThat(workTime.getOvertimeHalf(), is(0L));
        assertThat(workTime.getOvertimeFull(), is(0L));
    }

    @Test
    public void testAddOvertimeTwoAndHalfHour() throws Exception {
        WorkTime workTime = new WorkTime();
        workTimeCalculator.addOvertime(workTime, 60 * 10 + 30);
        assertThat(workTime.getOvertimeQuarter(), is(120L));
        assertThat(workTime.getOvertimeHalf(), is(30L));
        assertThat(workTime.getOvertimeFull(), is(0L));
    }

    @Test
    public void testAddOvertimeFourHour() throws Exception {
        WorkTime workTime = new WorkTime();
        workTimeCalculator.addOvertime(workTime, 60 * 12);
        assertThat(workTime.getOvertimeQuarter(), is(120L));
        assertThat(workTime.getOvertimeHalf(), is(120L));
        assertThat(workTime.getOvertimeFull(), is(0L));
    }

    @Test
    public void testAddOvertimeNineHour() throws Exception {
        WorkTime workTime = new WorkTime();
        workTimeCalculator.addOvertime(workTime, 60 * 17);
        assertThat(workTime.getOvertimeQuarter(), is(120L));
        assertThat(workTime.getOvertimeHalf(), is(120L));
        assertThat(workTime.getOvertimeFull(), is(5L * 60));
    }
}