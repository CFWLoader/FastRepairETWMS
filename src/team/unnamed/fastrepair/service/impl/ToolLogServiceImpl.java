package team.unnamed.fastrepair.service.impl;

import team.unnamed.fastrepair.dao.ToolLogDao;
import team.unnamed.fastrepair.dao.impl.MySqlToolLogDao;
import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.model.ExpensiveToolLog;
import team.unnamed.fastrepair.model.InexpensiveToolLog;
import team.unnamed.fastrepair.model.Tool;
import team.unnamed.fastrepair.service.ToolLogService;
import team.unnamed.fastrepair.service.ToolService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/12/15.
 */
public class ToolLogServiceImpl implements ToolLogService {

    private ToolLogDao toolLogDao;

    public ToolLogServiceImpl() {
        toolLogDao = new MySqlToolLogDao();
    }

    @Override
    public void addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws BadRequestParameterException {
        if(inexpensiveToolLog.getId() < 0)throw new BadRequestParameterException();

        toolLogDao.addInexpensiveToolLog(inexpensiveToolLog);
    }

    @Override
    public void updateInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException, BadRequestParameterException {
        if(inexpensiveToolLog.getId() < 0)throw new BadRequestParameterException();

        toolLogDao.updateInexpensiveToolLog(inexpensiveToolLog);
    }

    @Override
    public void removeInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException, BadRequestParameterException {
        if(inexpensiveToolLog.getId() < 0)throw new BadRequestParameterException();

        toolLogDao.removeInexpensiveToolLog(inexpensiveToolLog);
    }

    @Override
    public int getTotalOfInexpensiveToolLogByEmployeeId(int id) throws SQLException, BadRequestParameterException {
        if(id < 0)throw new BadRequestParameterException();

        return toolLogDao.getTotalOfInexpensiveToolLogByEmployeeId(id);
    }

    @Override
    public InexpensiveToolLog getInexpensiveToolLogById(int id) throws SQLException, BadRequestParameterException {
        if(id < 0)throw new BadRequestParameterException();

        return toolLogDao.getInexpensiveToolLogById(id);
    }

    @Override
    public List<InexpensiveToolLog> getInexpensiveToolLogsByEmployeeId(int employeeId, int pageNo, int size) throws SQLException, BadRequestParameterException {
        if(employeeId < 0)throw new BadRequestParameterException();

        return toolLogDao.getInexpensiveToolLogsByEmployeeId(employeeId, (pageNo - 1) * size, size);
    }

    @Override
    public void addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException, BadRequestParameterException {
        if(expensiveToolLog.getId() < 0)throw new BadRequestParameterException();

        toolLogDao.addExpensiveToolLog(expensiveToolLog);
    }

    @Override
    public void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException, BadRequestParameterException {
        if(expensiveToolLog.getId() < 0)throw new BadRequestParameterException();

        toolLogDao.updateExpensiveToolLog(expensiveToolLog);
    }

    @Override
    public void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException, BadRequestParameterException {
        if(expensiveToolLog.getId() < 0)throw new BadRequestParameterException();

        toolLogDao.removeExpensiveToolLog(expensiveToolLog);
    }

    @Override
    public int getTotalOfExpensiveToolLogByEmployeeId(int id) throws SQLException, BadRequestParameterException {
        if(id < 0)throw new BadRequestParameterException();

        return toolLogDao.getTotalOfExpensiveToolLogByEmployeeId(id);
    }

    @Override
    public ExpensiveToolLog getExpensiveToolLogById(int id) throws SQLException, BadRequestParameterException {
        if(id < 0)throw new BadRequestParameterException();

        return toolLogDao.getExpensiveToolLogById(id);
    }

    @Override
    public List<ExpensiveToolLog> getExpensiveTooLogsByEmployeeId(int employeeId, int pageNo, int size) throws SQLException, BadRequestParameterException {
        if(employeeId < 0)throw new BadRequestParameterException();

        return toolLogDao.getExpensiveTooLogsByEmployeeId(employeeId, (pageNo - 1) * size, size);
    }

    @Override
    public void close() throws SQLException {
        toolLogDao.close();
    }
}
