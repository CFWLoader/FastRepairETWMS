package pers.evan.fastrepair.dao;

import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.model.ExpensiveToolLog;
import pers.evan.fastrepair.model.InexpensiveToolLog;
import pers.evan.fastrepair.model.ToolLog;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolLogDao extends TemplateDao<ToolLog>{

    /*
    int addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog);

    void updateInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog);

    void removeInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog);
    */

    int getTotalOfInexpensiveToolLogByEmployee(Employee employee);

    //InexpensiveToolLog getInexpensiveToolLogBy(Employee employee);

    List<InexpensiveToolLog> getInexpensiveToolLogsByEmployee(Employee employee, int startIndex, int size);

    /*
    int addExpensiveToolLog(ExpensiveToolLog expensiveToolLog);

    void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog);

    void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog);
    */

    int getTotalOfExpensiveToolLogByEmployee(Employee employee);

    //ExpensiveToolLog getExpensiveToolLogBy(Employee employee);

    List<ExpensiveToolLog> getExpensiveTooLogsByEmployee(Employee employee, int startIndex, int size);

    /*
    boolean getAutoCommit();

    void setAutoCommit(boolean autoCommit);

    void commit();

    void close();
    */
}
