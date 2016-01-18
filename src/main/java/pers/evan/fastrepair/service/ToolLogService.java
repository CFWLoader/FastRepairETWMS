package pers.evan.fastrepair.service;

import pers.evan.fastrepair.exception.BadRequestParameterException;
import pers.evan.fastrepair.exception.TransactionCancelledException;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.model.ExpensiveToolLog;
import pers.evan.fastrepair.model.InexpensiveToolLog;
import pers.evan.fastrepair.model.Tool;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolLogService {

    int addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws Exception;

    void updateInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws BadRequestParameterException;

    void removeInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws BadRequestParameterException;

    int getTotalOfInexpensiveToolLogByEmployee(Employee employee) throws BadRequestParameterException;

    InexpensiveToolLog getInexpensiveToolLogById(long id) throws BadRequestParameterException;

    List<InexpensiveToolLog> getInexpensiveToolLogsByEmployee(Employee employee, int pageNo, int size) throws BadRequestParameterException;

    int addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws TransactionCancelledException;

    void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws BadRequestParameterException, TransactionCancelledException;

    void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws BadRequestParameterException;

    int getTotalOfExpensiveToolLogByEmployee(Employee employee) throws BadRequestParameterException;

    ExpensiveToolLog getExpensiveToolLogById(long id) throws BadRequestParameterException;

    List<ExpensiveToolLog> getExpensiveTooLogsByEmployee(Employee employee, int pageNo, int size) throws BadRequestParameterException;

    InexpensiveToolLog inexpensiveLogAssembler(String idStr, Employee employee, Tool tool, String quantityStr, String status, String logDate);

    ExpensiveToolLog expensiveLogAssembler(String id, Employee employee, Tool tool, String quantity, String status, String lentdate, String backdate);
}
