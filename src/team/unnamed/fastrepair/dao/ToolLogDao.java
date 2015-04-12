package team.unnamed.fastrepair.dao;

import team.unnamed.fastrepair.model.ExpensiveToolLog;
import team.unnamed.fastrepair.model.InexpensiveToolLog;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolLogDao {

    void addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog);

    void updateInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException;

    void removeInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException;

    InexpensiveToolLog getInexpensiveToolLogById(int id) throws SQLException;

    List<InexpensiveToolLog> getInexpensiveToolLogsByEmployeeId(int employeeId, int startIndex, int size) throws SQLException;

    void addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException;

    void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog);

    void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog);

    ExpensiveToolLog getExpensiveToolLogById(int id);

    List<ExpensiveToolLog> getExpensiveTooLogsByEmployeeId(int employeeId, int startIndex, int size);

    void close() throws SQLException;
}
