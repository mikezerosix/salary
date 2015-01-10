package mike.net.salary.writer;

import mike.net.salary.model.Salary;

public class ConsoleWriter {

    public void writeSalary(Salary salary) {
        System.out.printf("%d, %s\n", salary.getId(), salary.getName());
        System.out.printf("\t  Work hours   \t: $%,.2f \n",salary.getBaseSalary());
        System.out.printf("\t  Evening hours\t: $%,.2f \n",salary.getIrregularHoursSalary());
        System.out.printf("\t  Overtime 25%% \t: $%,.2f \n",salary.getOvertimeQuarter());
        System.out.printf("\t  Overtime 50%% \t: $%,.2f \n",salary.getOvertimeHalf());
        System.out.printf("\t  Overtime 100%%\t: $%,.2f \n",salary.getOvertimeFull());
        System.out.printf("\t  -----------------------\n");
        System.out.printf("\t          Total: $%,.2f  \n",  salary.getTotal());
        System.out.printf("\t          ===============\n\n");
    }

}
