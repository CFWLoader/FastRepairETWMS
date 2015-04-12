package team.unnamed.fastrepair.dao.impl.test;

import org.junit.Test;
import team.unnamed.fastrepair.dao.ToolLogDao;
import team.unnamed.fastrepair.dao.impl.MySqlToolLogDao;
import team.unnamed.fastrepair.model.ExpensiveToolLog;
import team.unnamed.fastrepair.model.InexpensiveToolLog;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by cfwloader on 4/12/15.
 */
public class MySqlToolLogDaoTest {
    @Test
    public void testAddInexpensiveToolLog() throws SQLException {
        ToolLogDao toolLogDao = new MySqlToolLogDao();

        InexpensiveToolLog inexpensiveToolLog = new InexpensiveToolLog();

        inexpensiveToolLog.setEmployeeId(2);
        inexpensiveToolLog.setToolId(2);
        inexpensiveToolLog.setQuantity(10);
        inexpensiveToolLog.setStatus("Lent");
        inexpensiveToolLog.setLogDate(new Date(System.currentTimeMillis()));

        toolLogDao.addInexpensiveToolLog(inexpensiveToolLog);

        toolLogDao.close();
    }

    @Test
    public void testGetInexpensiveToolLogById() throws SQLException {
        ToolLogDao toolLogDao = new MySqlToolLogDao();

        InexpensiveToolLog inexpensiveToolLog = toolLogDao.getInexpensiveToolLogById(1);

        System.out.println(inexpensiveToolLog);

        toolLogDao.close();
    }

    @Test
    public void testAddExpensiveToolLogById() throws SQLException {
        ToolLogDao toolLogDao = new MySqlToolLogDao();

        ExpensiveToolLog expensiveToolLog = new ExpensiveToolLog();

        expensiveToolLog.setEmployeeId(2);
        expensiveToolLog.setToolId(1);
        expensiveToolLog.setQuantity(20);
        expensiveToolLog.setStatus("Wait for deal");
        expensiveToolLog.setLendDate(new Date(System.currentTimeMillis()));

        toolLogDao.addExpensiveToolLog(expensiveToolLog);

        toolLogDao.close();
    }

    @Test
    public void testGetExpensiveToolLogById() throws SQLException {
        ToolLogDao toolLogDao = new MySqlToolLogDao();

        ExpensiveToolLog expensiveToolLog = toolLogDao.getExpensiveToolLogById(1);

        System.out.println(expensiveToolLog);

        toolLogDao.close();
    }

    @Test
    public void testGetInexpensiveToolLogByEmployeeId() throws SQLException {
        ToolLogDao toolLogDao = new MySqlToolLogDao();

        for(InexpensiveToolLog inexpensiveToolLog : toolLogDao.getInexpensiveToolLogsByEmployeeId(2, 0, 10)){
            System.out.println(inexpensiveToolLog);
        }

        toolLogDao.close();
    }

    @Test
    public void testGetExpensiveToolLogByEmployeeId() throws SQLException {
        ToolLogDao toolLogDao = new MySqlToolLogDao();

        for(ExpensiveToolLog expensiveToolLog : toolLogDao.getExpensiveTooLogsByEmployeeId(2, 0, 10)){
            System.out.println(expensiveToolLog);
        }

        toolLogDao.close();
    }
}
