package pers.evan.fastrepair.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pers.evan.fastrepair.dao.ToolLogDao;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.model.ExpensiveToolLog;
import pers.evan.fastrepair.model.InexpensiveToolLog;
import pers.evan.fastrepair.model.ToolLog;
import pers.evan.fastrepair.util.MySqlConnectionManager;

import javax.swing.plaf.nimbus.State;
import java.beans.*;
import java.net.ConnectException;
import java.sql.*;
import java.sql.Statement;
import java.util.*;
import java.util.Date;

/**
 * Created by cfwloader on 4/12/15.
 */
@Repository
public class MySqlToolLogDao extends TemplateDaoImpl<ToolLog> implements ToolLogDao {

    @Override
    public int getTotalOfInexpensiveToolLogByEmployee(Employee employee) {

        Criteria criteria = this.getSession().createCriteria(InexpensiveToolLog.class);

        criteria.add(Restrictions.eq("employee", employee));

        criteria.setProjection(Projections.count("id"));

        Integer result = (Integer) criteria.uniqueResult();

        return result;
    }

    @Override
    public List<InexpensiveToolLog> getInexpensiveToolLogsByEmployee(Employee employee, int startIndex, int size) {

        DetachedCriteria criteria = DetachedCriteria.forClass(InexpensiveToolLog.class);

        criteria.add(Restrictions.eq("employee", employee));

        return (List<InexpensiveToolLog>) hibernateTemplate.findByCriteria(criteria, startIndex, size);
    }

    @Override
    public int getTotalOfExpensiveToolLogByEmployee(Employee employee) {

        Criteria criteria = this.getSession().createCriteria(ExpensiveToolLog.class);

        criteria.add(Restrictions.eq("employee", employee));

        criteria.setProjection(Projections.count("id"));

        Integer result = (Integer) criteria.uniqueResult();

        return result;
    }

    @Override
    public List<ExpensiveToolLog> getExpensiveTooLogsByEmployee(Employee employee, int startIndex, int size) {

        DetachedCriteria criteria = DetachedCriteria.forClass(ExpensiveToolLog.class);

        criteria.add(Restrictions.eq("employee", employee));

        return (List<ExpensiveToolLog>) hibernateTemplate.findByCriteria(criteria, startIndex, size);
    }

    /*

    private Connection connection;

    public MySqlToolLogDao() {
        connection = MySqlConnectionManager.getDefaultConnection();
    }


    @Override
    public int addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) {

        String sql = "insert into inexpensivetoollog values(null, ?, ?, ?, ?, ?);";

        PreparedStatement statement;

        ResultSet resultSet = null;

        int id = -1;

        try{
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, inexpensiveToolLog.getEmployeeId());
            statement.setInt(2, inexpensiveToolLog.getToolId());
            statement.setInt(3, inexpensiveToolLog.getQuantity());
            statement.setString(4, inexpensiveToolLog.getStatus());
            statement.setTimestamp(5, new Timestamp(inexpensiveToolLog.getLogDate().getTime()));

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                id = resultSet.getInt(1);
            }

            resultSet.close();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void updateInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException {

        String sql = "update inexpensivetoollog set employeeid = ?, toolid = ?, quantity = ?, stat = ? where id = " + inexpensiveToolLog.getId() + ";";

        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(sql);

            statement.setInt(1, inexpensiveToolLog.getEmployeeId());
            statement.setInt(2, inexpensiveToolLog.getToolId());
            statement.setInt(3, inexpensiveToolLog.getQuantity());
            statement.setString(4, inexpensiveToolLog.getStatus());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
    }

    @Override
    public void removeInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) throws SQLException {

        String sql = "delete from inexpensivetoollog where id = " + inexpensiveToolLog.getId() + ";";

        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(sql);

            statement.setInt(1, inexpensiveToolLog.getEmployeeId());
            statement.setInt(2, inexpensiveToolLog.getToolId());
            statement.setInt(3, inexpensiveToolLog.getQuantity());
            statement.setString(4, inexpensiveToolLog.getStatus());
            statement.setInt(5, inexpensiveToolLog.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
    }

    @Override
    public int getTotalOfInexpensiveToolLogByEmployeeId(int id) throws SQLException {

        int result = 0;

        String sql = "select count(*) from inexpensivetoollog where employeeid = " + id + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if(resultSet.next()){
                result = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            if(resultSet != null && !resultSet.isClosed())resultSet.close();

            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }

        return result;
    }

    @Override
    public InexpensiveToolLog getInexpensiveToolLogById(int id) throws SQLException {

        String sql = "select * from inexpensivetoollog where id = " + id + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        InexpensiveToolLog inexpensiveToolLog = null;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if(resultSet.next()){
                inexpensiveToolLog = new InexpensiveToolLog();

                inexpensiveToolLog.setId(resultSet.getInt(1));
                inexpensiveToolLog.setEmployeeId(resultSet.getInt(2));
                inexpensiveToolLog.setToolId(resultSet.getInt(3));
                inexpensiveToolLog.setQuantity(resultSet.getInt(4));
                inexpensiveToolLog.setStatus(resultSet.getString(5));
                inexpensiveToolLog.setLogDate(new Date(resultSet.getTimestamp(6).getTime()));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            if(resultSet != null && !resultSet.isClosed())resultSet.close();

            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
        return inexpensiveToolLog;
    }

    @Override
    public List<InexpensiveToolLog> getInexpensiveToolLogsByEmployeeId(int employeeId, int startIndex, int size) throws SQLException {

        String sql = "select * from inexpensivetoollog where employeeid = " + employeeId + " limit " + startIndex + ", " + size + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        InexpensiveToolLog inexpensiveToolLog = null;

        List<InexpensiveToolLog> inexpensiveToolLogs = new ArrayList<InexpensiveToolLog>(size);

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            while(resultSet.next()){
                inexpensiveToolLog = new InexpensiveToolLog();

                inexpensiveToolLog.setId(resultSet.getInt(1));
                inexpensiveToolLog.setEmployeeId(resultSet.getInt(2));
                inexpensiveToolLog.setToolId(resultSet.getInt(3));
                inexpensiveToolLog.setQuantity(resultSet.getInt(4));
                inexpensiveToolLog.setStatus(resultSet.getString(5));
                inexpensiveToolLog.setLogDate(new Date(resultSet.getTimestamp(6).getTime()));

                inexpensiveToolLogs.add(inexpensiveToolLog);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            if(resultSet != null && !resultSet.isClosed())resultSet.close();

            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
        return inexpensiveToolLogs;
    }

    @Override
    public int addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException {

        String sql = "insert into expensivetoollog(employeeid, toolid, quantity, stat, lenddate) values(?, ?, ?, ?, ?);";

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        int id = -1;

        try{
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, expensiveToolLog.getEmployeeId());
            statement.setInt(2, expensiveToolLog.getToolId());
            statement.setInt(3, expensiveToolLog.getQuantity());
            statement.setString(4, expensiveToolLog.getStatus());
            statement.setTimestamp(5, new Timestamp(expensiveToolLog.getLendDate().getTime()));

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                id = resultSet.getInt(1);
            }

            resultSet.close();

            statement.close();
        } catch (SQLException e) {
            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }

        return id;
    }

    @Override
    public void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException {
        String sql = "update expensivetoollog set employeeid = ?, toolid = ?, quantity = ?, stat = ?, backdate = ? where id = ?;";

        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(sql);

            statement.setInt(1, expensiveToolLog.getEmployeeId());
            statement.setInt(2, expensiveToolLog.getToolId());
            statement.setInt(3, expensiveToolLog.getQuantity());
            statement.setString(4, expensiveToolLog.getStatus());
            statement.setTimestamp(5, new Timestamp(expensiveToolLog.getBackDate().getTime()));
            statement.setInt(6, expensiveToolLog.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
    }

    @Override
    public void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException {
        String sql = "delete from expensivetoollog where id = " + expensiveToolLog.getId() + ";";

        Statement statement = null;

        try{
            statement = connection.createStatement();

            statement.executeUpdate(sql);

            statement.close();
        } catch (SQLException e) {
            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
    }

    @Override
    public int getTotalOfExpensiveToolLogByEmployeeId(int id) throws SQLException {
        int result = 0;

        String sql = "select count(*) from expensivetoollog where employeeid = " + id + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if(resultSet.next()){
                result = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            if(resultSet != null && !resultSet.isClosed())resultSet.close();

            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }

        return result;
    }

    @Override
    public ExpensiveToolLog getExpensiveToolLogById(int id) throws SQLException {

        String sql = "select * from expensivetoollog where id = " + id + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        ExpensiveToolLog expensiveToolLog = null;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if(resultSet.next()){
                expensiveToolLog = new ExpensiveToolLog();

                expensiveToolLog.setId(resultSet.getInt(1));
                expensiveToolLog.setEmployeeId(resultSet.getInt(2));
                expensiveToolLog.setToolId(resultSet.getInt(3));
                expensiveToolLog.setQuantity(resultSet.getInt(4));
                expensiveToolLog.setStatus(resultSet.getString(5));
                expensiveToolLog.setLendDate(new Date(resultSet.getTimestamp(6).getTime()));
                expensiveToolLog.setBackDate(new Date(resultSet.getTimestamp(7).getTime()));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            if(resultSet != null && !resultSet.isClosed())resultSet.close();

            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
        return expensiveToolLog;
    }

    @Override
    public List<ExpensiveToolLog> getExpensiveTooLogsByEmployeeId(int employeeId, int startIndex, int size) throws SQLException {

        String sql = "select * from expensivetoollog where employeeid = " + employeeId + " limit " + startIndex + ", " + size + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        ExpensiveToolLog expensiveToolLog = null;

        List<ExpensiveToolLog> expensiveToolLogs = new ArrayList<ExpensiveToolLog>(size);

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            while(resultSet.next()){
                expensiveToolLog = new ExpensiveToolLog();

                expensiveToolLog.setId(resultSet.getInt(1));
                expensiveToolLog.setEmployeeId(resultSet.getInt(2));
                expensiveToolLog.setToolId(resultSet.getInt(3));
                expensiveToolLog.setQuantity(resultSet.getInt(4));
                expensiveToolLog.setStatus(resultSet.getString(5));
                expensiveToolLog.setLendDate(new Date(resultSet.getTimestamp(6).getTime()));
                expensiveToolLog.setBackDate(new Date(resultSet.getTimestamp(7).getTime()));

                expensiveToolLogs.add(expensiveToolLog);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            if(resultSet != null && !resultSet.isClosed())resultSet.close();

            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
        return expensiveToolLogs;
    }


    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    public void close() throws SQLException {
        try{
            connection.close();
        } catch (SQLException e) {
            if(connection != null && !connection.isClosed())connection.close();
        }
    }
    */
}
