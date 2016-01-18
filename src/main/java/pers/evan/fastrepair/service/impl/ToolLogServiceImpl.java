package pers.evan.fastrepair.service.impl;

import org.springframework.stereotype.Service;
import pers.evan.fastrepair.dao.ToolDao;
import pers.evan.fastrepair.dao.ToolLogDao;
import pers.evan.fastrepair.dao.impl.MySqlToolDao;
import pers.evan.fastrepair.dao.impl.MySqlToolLogDao;
import pers.evan.fastrepair.exception.BadRequestParameterException;
import pers.evan.fastrepair.exception.TransactionCancelledException;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.model.ExpensiveToolLog;
import pers.evan.fastrepair.model.InexpensiveToolLog;
import pers.evan.fastrepair.model.Tool;
import pers.evan.fastrepair.service.ToolLogService;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by cfwloader on 4/12/15.
 */
@Service
public class ToolLogServiceImpl implements ToolLogService {

    @Resource
    private ToolLogDao toolLogDao;

    @Resource
    private ToolDao toolDao;

    @Override
    public int addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws Exception {
        //if(inexpensiveToolLog.getId() < 0)throw new BadRequestParameterException();
        inexpensiveToolLog.setStatus("Waiting for deal");

        Tool tool = toolDao.getToolById(inexpensiveToolLog.getId());

        if (tool.getNumberOfAvailable() - inexpensiveToolLog.getQuantity() < 0)
            throw new TransactionCancelledException();

        int currentNumber = tool.getNumberOfAvailable();

        tool.setNumberOfAvailable(currentNumber - inexpensiveToolLog.getQuantity());

        toolDao.updateEntity(tool);

        toolLogDao.addEntity(inexpensiveToolLog);

        return 0;
    }

    @Override
    public void updateInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws BadRequestParameterException {
        if (inexpensiveToolLog.getId() < 0) throw new BadRequestParameterException();

        toolLogDao.updateEntity(inexpensiveToolLog);
    }

    @Override
    public void removeInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws BadRequestParameterException {
        if (inexpensiveToolLog.getId() < 0) throw new BadRequestParameterException();

        toolLogDao.deleteEntity(inexpensiveToolLog);
    }

    @Override
    public int getTotalOfInexpensiveToolLogByEmployee(Employee employee) throws BadRequestParameterException {
        if (employee == null) throw new BadRequestParameterException();

        return toolLogDao.getTotalOfInexpensiveToolLogByEmployee(employee);
    }

    @Override
    public InexpensiveToolLog getInexpensiveToolLogById(long id) throws BadRequestParameterException {
        if (id < 0) throw new BadRequestParameterException();

        return (InexpensiveToolLog) toolLogDao.getEntityById(id);
    }

    @Override
    public List<InexpensiveToolLog> getInexpensiveToolLogsByEmployee(Employee employee, int pageNo, int size) throws BadRequestParameterException {
        if (employee == null) throw new BadRequestParameterException();

        return toolLogDao.getInexpensiveToolLogsByEmployee(employee, (pageNo - 1) * size, size);
    }

    @Override
    public int addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws TransactionCancelledException {

        Tool tool = toolDao.getToolById(expensiveToolLog.getId());

        if (tool.getNumberOfAvailable() - expensiveToolLog.getQuantity() < 0)
            throw new TransactionCancelledException();

        int currentNumber = tool.getNumberOfAvailable();

        tool.setNumberOfAvailable(currentNumber - expensiveToolLog.getQuantity());

        toolDao.updateEntity(tool);

        toolLogDao.addEntity(expensiveToolLog);

        return 0;
    }

    @Override
    public void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws BadRequestParameterException, TransactionCancelledException {

        if (expensiveToolLog.getId() < 0) throw new BadRequestParameterException();

        if (expensiveToolLog.getStatus().equals("Back")) {

            Tool tool = toolDao.getToolById(expensiveToolLog.getId());

            if (tool.getNumberOfAvailable() - expensiveToolLog.getQuantity() < 0)
                throw new TransactionCancelledException();

            int currentNumber = tool.getNumberOfAvailable();

            tool.setNumberOfAvailable(currentNumber + expensiveToolLog.getQuantity());

            toolDao.updateEntity(tool);

            expensiveToolLog.setBackDate(new Date(System.currentTimeMillis()));


            toolLogDao.updateEntity(expensiveToolLog);

        }

    }

    @Override
    public void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws BadRequestParameterException
    {
        if (expensiveToolLog == null) throw new BadRequestParameterException();

        toolLogDao.deleteEntity(expensiveToolLog);
    }

    @Override
    public int getTotalOfExpensiveToolLogByEmployee(Employee employee) throws BadRequestParameterException {
        if (employee == null) throw new BadRequestParameterException();

        return toolLogDao.getTotalOfExpensiveToolLogByEmployee(employee);
    }

    @Override
    public ExpensiveToolLog getExpensiveToolLogById(long id) throws BadRequestParameterException {
        if (id < 0) throw new BadRequestParameterException();

        return (ExpensiveToolLog) toolLogDao.getEntityById(id);
    }

    @Override
    public List<ExpensiveToolLog> getExpensiveTooLogsByEmployee(Employee employee, int pageNo, int size) throws
            BadRequestParameterException {
        if (employee == null) throw new BadRequestParameterException();

        return toolLogDao.getExpensiveTooLogsByEmployee(employee, (pageNo - 1) * size, size);
    }

    @Override
    public InexpensiveToolLog inexpensiveLogAssembler(String idStr, Employee employee, Tool tool, String
            quantityStr, String status, String logDate) {

        InexpensiveToolLog inexpensiveToolLog = new InexpensiveToolLog();

        if (idStr != null && !idStr.trim().equals("")) inexpensiveToolLog.setId(Integer.parseInt(idStr));

        inexpensiveToolLog.setEmployee(employee);

        inexpensiveToolLog.setTool(tool);

        if (quantityStr != null && !quantityStr.trim().equals(""))
            inexpensiveToolLog.setQuantity(Integer.parseInt(quantityStr));

        if (status != null && !status.equals("")) inexpensiveToolLog.setStatus(status);

        //System.out.println(logDate);

        if (logDate != null && !logDate.trim().equals(""))
            inexpensiveToolLog.setLogDate(new Date(Long.parseLong(logDate.trim())));

        return inexpensiveToolLog;
    }

    @Override
    public ExpensiveToolLog expensiveLogAssembler(String idStr, Employee employee, Tool tool, String
            quantityStr, String status, String lentDate, String backDate) {

        ExpensiveToolLog expensiveToolLog = new ExpensiveToolLog();

        if (idStr != null && !idStr.trim().equals("")) expensiveToolLog.setId(Integer.parseInt(idStr));

        expensiveToolLog.setEmployee(employee);

        expensiveToolLog.setTool(tool);

        if (quantityStr != null && !quantityStr.trim().equals(""))
            expensiveToolLog.setQuantity(Integer.parseInt(quantityStr));

        if (status != null && !status.equals("")) expensiveToolLog.setStatus(status);

        //System.out.println(logDate);

        if (lentDate != null && !lentDate.trim().equals(""))
            expensiveToolLog.setLendDate(new Date(Long.parseLong(lentDate.trim())));

        if (backDate != null && !backDate.trim().equals(""))
            expensiveToolLog.setBackDate(new Date(Long.parseLong(backDate.trim())));

        return expensiveToolLog;
    }

}
