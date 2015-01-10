package mike.net.salary.writer;

import mike.net.salary.model.Salary;

public class ConsoleWriter {

    public void writeSalary(Salary salary) {
        System.out.printf("%d %s, Total: $%,.2f \n", salary.getId(), salary.getName(), salary.getTotal());
        System.out.printf("\t  Base salary   : $%,.2f \n",salary.getBaseSalary());
        System.out.printf("\t  Evening hours : $%,.2f \n",salary.getIrregularHoursSalary());
        System.out.printf("\t  Overtime 25%% : $%,.2f \n",salary.getOvertimeQuarter());
        System.out.printf("\t  Overtime 50%% : $%,.2f \n",salary.getOvertimeHalf());
        System.out.printf("\t  Overtime 100%%: $%,.2f \n",salary.getOvertimeFull());
    }

}
