package team.unnamed.fastrepair.dao.impl;

import team.unnamed.fastrepair.dao.ToolLogDao;
import team.unnamed.fastrepair.model.ExpensiveToolLog;
import team.unnamed.fastrepair.model.InexpensiveToolLog;
import team.unnamed.fastrepair.util.MySqlConnectionManager;

import java.net.ConnectException;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by cfwloader on 4/12/15.
 */
public class MySqlToolLogDao implements ToolLogDao {

    private Connection connection;

    public MySqlToolLogDao() {
        connection = MySqlConnectionManager.getDefaultConnection();
    }

    @Override
    public void addInexpensiveToolLog(InexpensiveToolLog inexpensiveToolLog) {

        String sql = "insert into inexpensivetoollog values(null, ?, ?, ?, ?, ?);";

        PreparedStatement statement;

        try{
            statement = connection.prepareStatement(sql);

            statement.setInt(1, inexpensiveToolLog.getEmployeeId());
            statement.setInt(2, inexpensiveToolLog.getToolId());
            statement.setInt(3, inexpensiveToolLog.getQuantity());
            statement.setString(4, inexpensiveToolLog.getStatus());
            statement.setTimestamp(5, new Timestamp(inexpensiveToolLog.getLogDate().getTime()));

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            statement.setInt(5, inexpensiveToolLog.getId());

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

        String sql = "select * from inexpensivetoollog where employeeid = " + employeeId + ";";

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
    public void addExpensiveToolLog(ExpensiveToolLog expensiveToolLog) throws SQLException {

        String sql = "insert into expensivetoollog(employeeid, toolid, quantity, stat, lenddate) values(?, ?, ?, ?, ?);";

        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(sql);

            statement.setInt(1, expensiveToolLog.getEmployeeId());
            statement.setInt(2, expensiveToolLog.getToolId());
            statement.setInt(3, expensiveToolLog.getQuantity());
            statement.setString(4, expensiveToolLog.getStatus());
            statement.setTimestamp(5, new Timestamp(expensiveToolLog.getLendDate().getTime()));

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }
    }

    @Override
    public void updateExpensiveToolLog(ExpensiveToolLog expensiveToolLog) {

    }

    @Override
    public void removeExpensiveToolLog(ExpensiveToolLog expensiveToolLog) {

    }

    @Override
    public ExpensiveToolLog getExpensiveToolLogById(int id) {
        return null;
    }

    @Override
    public List<ExpensiveToolLog> getExpensiveTooLogsByEmployeeId(int employeeId, int startIndex, int size) {
        return null;
    }

    @Override
    public void close() throws SQLException {
        try{
            connection.close();
        } catch (SQLException e) {
            if(connection != null && !connection.isClosed())connection.close();
        }
    }
}
