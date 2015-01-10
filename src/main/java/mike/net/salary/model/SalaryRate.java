package mike.net.salary.model;

public class SalaryRate {
    private double baseRate;
    private double irregularRate;
    private double overtimeQuarterRate;
    private double overtimeHalfRate;
    private double getOvertimeFullRate;

    public SalaryRate(double baseRate, double irregularRate, double overtimeQuarterRate, double overtimeHalfRate, double getOvertimeFullRate) {
        this.baseRate = baseRate;
        this.irregularRate = irregularRate;
        this.overtimeQuarterRate = overtimeQuarterRate;
        this.overtimeHalfRate = overtimeHalfRate;
        this.getOvertimeFullRate = getOvertimeFullRate;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public double getIrregularRate() {
        return irregularRate;
    }

    public double getOvertimeQuarterRate() {
        return overtimeQuarterRate;
    }

    public double getOvertimeHalfRate() {
        return overtimeHalfRate;
    }

    public double getGetOvertimeFullRate() {
        return getOvertimeFullRate;
    }
}
