package mike.net.salary.model;

public class WorkTime {
    private long base;
    private long irregular;
    private long overtimeQuarter;
    private long overtimeHalf;
    private long overtimeFull;

    public long getBase() {
        return base;
    }

    public long getIrregular() {
        return irregular;
    }

    public long getOvertimeQuarter() {
        return overtimeQuarter;
    }

    public long getOvertimeHalf() {
        return overtimeHalf;
    }

    public long getOvertimeFull() {
        return overtimeFull;
    }

    public void addToBase(long base) {
        this.base += base;
    }

    public void addToIrregular(long irregular) {
        this.irregular += irregular;
    }

    public void addToOvertimeQuarter(long overtimeQuarter) {
        this.overtimeQuarter += overtimeQuarter;
    }

    public void addToOvertimeHalf(long overtimeHalf) {
        this.overtimeHalf += overtimeHalf;
    }

    public void addToOvertimeFull(long overtimeFull) {
        this.overtimeFull += overtimeFull;
    }
}
