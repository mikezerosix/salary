package mike.net.salary.calculator;

import mike.net.salary.model.Salary;
import mike.net.salary.model.SalaryRate;
import mike.net.salary.model.WorkTime;

public class SalaryBuilder {
    private long id;
    private String name;
    private SalaryRate salaryRate;
    private WorkTime workTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalaryRate(SalaryRate salaryRate) {
        this.salaryRate = salaryRate;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public Salary build() {
        return new Salary(id, name, salaryRate, workTime);
    }
}
