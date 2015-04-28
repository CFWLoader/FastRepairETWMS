package team.unnamed.fastrepair.dao;

import team.unnamed.fastrepair.model.ExpensiveToolLog;
import team.unnamed.fastrepair.model.InexpensiveToolLog;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolLogDao {

    int addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog);

    void updateInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException;

    void removeInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException;

    int getTotalOfInexpensiveToolLogByEmployeeId(int id) throws SQLException;

    InexpensiveToolLog getInexpensiveToolLogById(int id) throws SQLException;

    List<InexpensiveToolLog> getInexpensiveToolLogsByEmployeeId(int employeeId, int startIndex, int size) throws SQLException;

    int addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException;

    void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException;

    void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException;

    int getTotalOfExpensiveToolLogByEmployeeId(int id) throws SQLException;

    ExpensiveToolLog getExpensiveToolLogById(int id) throws SQLException;

    List<ExpensiveToolLog> getExpensiveTooLogsByEmployeeId(int employeeId, int startIndex, int size) throws SQLException;

    boolean getAutoCommit() throws SQLException;

    void setAutoCommit(boolean autoCommit) throws SQLException;

    void commit() throws SQLException;

    void close() throws SQLException;
}
