package team.unnamed.fastrepair.service;

import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.model.ExpensiveToolLog;
import team.unnamed.fastrepair.model.InexpensiveToolLog;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolLogService {

    int addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws Exception;

    void updateInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException, BadRequestParameterException;

    void removeInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException, BadRequestParameterException;

    int getTotalOfInexpensiveToolLogByEmployeeId(int id) throws SQLException, BadRequestParameterException;

    InexpensiveToolLog getInexpensiveToolLogById(int id) throws SQLException, BadRequestParameterException;

    List<InexpensiveToolLog> getInexpensiveToolLogsByEmployeeId(int employeeId, int pageNo, int size) throws SQLException, BadRequestParameterException;

    int addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException;

    void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException, BadRequestParameterException;

    void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException, BadRequestParameterException;

    int getTotalOfExpensiveToolLogByEmployeeId(int id) throws SQLException, BadRequestParameterException;

    ExpensiveToolLog getExpensiveToolLogById(int id) throws SQLException, BadRequestParameterException;

    List<ExpensiveToolLog> getExpensiveTooLogsByEmployeeId(int employeeId, int pageNo, int size) throws SQLException, BadRequestParameterException;

    InexpensiveToolLog inexpensiveLogAssembler(String idStr, String employeeId, String toolId, String quantityStr, String status, String logDate);

    ExpensiveToolLog expensiveLogAssembler(String id, String employeeid, String toolid, String quantity, String status, String lentdate, String backdate);

    void close() throws SQLException;
}
