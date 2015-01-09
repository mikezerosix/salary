package mike.net.salary.calculator;

import mike.net.salary.model.SalaryRate;

public class SalaryCalculator {
    public static final double BASE_RATE = 3.75;
    public static final double IRREGULAR_RATE = 1.15;

    public SalaryRate getSalaryRate() {
        return new SalaryRate(BASE_RATE, IRREGULAR_RATE, BASE_RATE * 0.25, BASE_RATE * 0.5, BASE_RATE);
    }


}
