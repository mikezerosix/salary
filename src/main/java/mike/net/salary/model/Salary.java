package mike.net.salary.model;

public class Salary {
    private final long id;
    private final String name;
    private final SalaryRate salaryRate;
    private final WorkTime workTime;

    public Salary(long id, String name, SalaryRate salaryRate, WorkTime workTime) {
        this.id = id;
        this.name = name;
        this.salaryRate = salaryRate;
        this.workTime = workTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public double getBaseSalary() {
        return workTime.getBase() * salaryRate.getBaseRate();
    }
    
    public double getIrregularHoursSalary() {
        return workTime.getIrregular() * salaryRate.getIrregularRate();
    }

    public double getOvertimeQuarter() {
        return workTime.getOvertimeQuarter() * salaryRate.getOvertimeQuarterRate();
    }
    
    public double getOvertimeHalf() {
        return workTime.getOvertimeHalf() * salaryRate.getOvertimeHalfRate();
    }

    public double getOvertimeFull() {
        return workTime.getOvertimeFull() * salaryRate.getGetOvertimeFullRate();
    }

    public double getTotal() {
        return getBaseSalary() + getIrregularHoursSalary() + getOvertimeQuarter() + getOvertimeHalf() + getOvertimeFull();
    }
}
