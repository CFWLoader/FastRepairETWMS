package team.unnamed.fastrepair.service.impl;

import team.unnamed.fastrepair.dao.ToolDao;
import team.unnamed.fastrepair.dao.ToolLogDao;
import team.unnamed.fastrepair.dao.impl.MySqlToolDao;
import team.unnamed.fastrepair.dao.impl.MySqlToolLogDao;
import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.exception.TransactionCancelledException;
import team.unnamed.fastrepair.model.ExpensiveToolLog;
import team.unnamed.fastrepair.model.InexpensiveToolLog;
import team.unnamed.fastrepair.model.Tool;
import team.unnamed.fastrepair.service.ToolLogService;

import java.sql.SQLException;
import java.util.Date;
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
    public int addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws Exception {
        //if(inexpensiveToolLog.getId() < 0)throw new BadRequestParameterException();
        inexpensiveToolLog.setStatus("Waiting for deal");

        ToolDao toolDao = new MySqlToolDao();

        try {
            Tool tool = toolDao.getToolById(inexpensiveToolLog.getToolId());

            if(tool.getNumberOfAvailable() - inexpensiveToolLog.getQuantity() < 0)throw new TransactionCancelledException();

            int currentNumber = tool.getNumberOfAvailable();

            tool.setNumberOfAvailable(currentNumber - inexpensiveToolLog.getQuantity());

            toolDao.updateTool(tool);
        } catch (SQLException e) {
            throw new TransactionCancelledException();
        }

        toolDao.close();

        return toolLogDao.addInexpensiveToolLog(inexpensiveToolLog);
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
    public int addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException, TransactionCancelledException {
        ToolDao toolDao = new MySqlToolDao();

        try {
            Tool tool = toolDao.getToolById(expensiveToolLog.getToolId());

            if(tool.getNumberOfAvailable() - expensiveToolLog.getQuantity() < 0)throw new TransactionCancelledException();

            int currentNumber = tool.getNumberOfAvailable();

            tool.setNumberOfAvailable(currentNumber - expensiveToolLog.getQuantity());

            toolDao.updateTool(tool);
        } catch (SQLException e) {
            throw new TransactionCancelledException();
        }

        toolDao.close();

        return toolLogDao.addExpensiveToolLog(expensiveToolLog);
    }

    @Override
    public void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException, BadRequestParameterException, TransactionCancelledException {
        if(expensiveToolLog.getId() < 0)throw new BadRequestParameterException();

        if(expensiveToolLog.getStatus().equals("Back")){
            ToolDao toolDao = new MySqlToolDao();

            try {
                Tool tool = toolDao.getToolById(expensiveToolLog.getToolId());

                if(tool.getNumberOfAvailable() - expensiveToolLog.getQuantity() < 0)throw new TransactionCancelledException();

                int currentNumber = tool.getNumberOfAvailable();

                tool.setNumberOfAvailable(currentNumber + expensiveToolLog.getQuantity());

                toolDao.updateTool(tool);

                expensiveToolLog.setBackDate(new Date(System.currentTimeMillis()));
            } catch (SQLException e) {
                throw new TransactionCancelledException();
            }

            toolDao.close();
        }

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
    public InexpensiveToolLog inexpensiveLogAssembler(String idStr, String employeeIdStr, String toolIdStr, String quantityStr, String status, String logDate) {

        InexpensiveToolLog inexpensiveToolLog = new InexpensiveToolLog();

        if(idStr != null && !idStr.trim().equals(""))inexpensiveToolLog.setId(Integer.parseInt(idStr));

        if(employeeIdStr != null && !employeeIdStr.trim().equals(""))inexpensiveToolLog.setEmployeeId(Integer.parseInt(employeeIdStr));

        if(toolIdStr != null && !toolIdStr.trim().equals(""))inexpensiveToolLog.setToolId(Integer.parseInt(toolIdStr));

        if(quantityStr != null && !quantityStr.trim().equals(""))inexpensiveToolLog.setQuantity(Integer.parseInt(quantityStr));

        if(status != null && !status.equals(""))inexpensiveToolLog.setStatus(status);

        //System.out.println(logDate);

        if(logDate != null && !logDate.trim().equals(""))inexpensiveToolLog.setLogDate(new Date(Long.parseLong(logDate.trim())));

        return inexpensiveToolLog;
    }

    @Override
    public ExpensiveToolLog expensiveLogAssembler(String idStr, String employeeIdStr, String toolIdStr, String quantityStr, String status, String lentDate, String backDate) {

        ExpensiveToolLog expensiveToolLog = new ExpensiveToolLog();

        if(idStr != null && !idStr.trim().equals(""))expensiveToolLog.setId(Integer.parseInt(idStr));

        if(employeeIdStr != null && !employeeIdStr.trim().equals(""))expensiveToolLog.setEmployeeId(Integer.parseInt(employeeIdStr));

        if(toolIdStr != null && !toolIdStr.trim().equals(""))expensiveToolLog.setToolId(Integer.parseInt(toolIdStr));

        if(quantityStr != null && !quantityStr.trim().equals(""))expensiveToolLog.setQuantity(Integer.parseInt(quantityStr));

        if(status != null && !status.equals(""))expensiveToolLog.setStatus(status);

        //System.out.println(logDate);

        if(lentDate != null && !lentDate.trim().equals(""))expensiveToolLog.setLendDate(new Date(Long.parseLong(lentDate.trim())));

        if(backDate != null && !backDate.trim().equals(""))expensiveToolLog.setBackDate(new Date(Long.parseLong(backDate.trim())));

        return expensiveToolLog;
    }

    @Override
    public void close() throws SQLException {
        toolLogDao.close();
    }
}
