package team.unnamed.fastrepair.dao.impl;

import team.unnamed.fastrepair.dao.CompanyDao;
import team.unnamed.fastrepair.dao.DepartmentDao;
import team.unnamed.fastrepair.dao.ToolDao;
import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.model.Tool;
import team.unnamed.fastrepair.util.MySqlConnectionManager;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cfwloader on 4/10/15.
 */
public class MySqlToolDao implements ToolDao {

    private Connection connection;

    public MySqlToolDao() {
        this.connection = MySqlConnectionManager.getDefaultConnection();
    }

    @Override
    public int addTool(Tool tool) throws SQLException {

        String sql = "insert into tool values(null, ?, ?, ?, ?, ?);";

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        int id = -1;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, tool.getToolName());
            statement.setBoolean(2, tool.isExpensive());
            statement.setInt(3, tool.getNumberOfAvailable());
            statement.setInt(4, tool.getCompanyId());
            statement.setInt(5, tool.getDepartmentId());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            if(resultSet.next())id = resultSet.getInt(1);

            resultSet.close();

            statement.close();
        } catch (SQLException e) {
            if(resultSet != null && !resultSet.isClosed())resultSet.close();

            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }

        return id;
    }

    @Override
    public Tool getToolById(int id) throws SQLException {

        String sql = "select * from tool where id = " + id + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        Tool tool = null;

        CompanyDao companyDao = new MySqlCompanyDao();

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        try {
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                tool = new Tool();

                tool.setId(resultSet.getInt(1));
                tool.setToolName(resultSet.getString(2));
                tool.setIsExpensive(resultSet.getBoolean(3));
                tool.setNumberOfAvailable(resultSet.getInt(4));
                tool.setCompanyId(resultSet.getInt(5));
                tool.setDepartmentId(resultSet.getInt(6));
                tool.setCompany(companyDao.getCompanyById(tool.getCompanyId()));
                tool.setDepartment(departmentDao.getDepartmentById(tool.getDepartmentId()));
            }

            companyDao.close();
            departmentDao.close();
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            if (resultSet != null && !resultSet.isClosed()) resultSet.close();

            if (statement != null && !statement.isClosed()) statement.close();

            throw e;
        }

        return tool;
    }

    @Override
    public void updateTool(Tool tool) throws SQLException {

        String sql = "update tool set toolname = ?, isexpensive = ?, numberofavailable = ?, companyid = ?, departmentid = ? where id = ?;";

        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(sql);

            statement.setString(1, tool.getToolName());
            statement.setBoolean(2, tool.isExpensive());
            statement.setInt(3, tool.getNumberOfAvailable());
            statement.setInt(4, tool.getCompanyId());
            statement.setInt(5, tool.getDepartmentId());
            statement.setInt(6, tool.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

            if(statement != null && !statement.isClosed())statement.close();
        }
    }

    @Override
    public void removeTool(Tool tool) throws SQLException {

        String sql = "delete from tool where id = ?;";

        PreparedStatement statement = null;

        try{
            statement.setInt(1, tool.getId());

            statement = connection.prepareStatement(sql);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

            if(statement != null && !statement.isClosed())statement.close();
        }
    }

    @Override
    public List<Tool> getToolsByDepartment(Department department, int startIndex, int size) throws SQLException {

        List<Tool> tools = new LinkedList<Tool>();

        String sql = "select * from tool where departmentid = " + department.getId() + " limit " + startIndex + "," + size + ";";

        CompanyDao companyDao = new MySqlCompanyDao();

        Map<Integer, Company> companyMap = new HashMap<Integer, Company>();

        Statement statement = null;

        ResultSet resultSet = null;

        Tool tool = null;

        try {
            statement = connection.createStatement();

            statement.execute(sql);

            for(Company company : companyDao.getCompanies()){
                companyMap.put(company.getId(), company);
            }

            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                tool = new Tool();

                tool.setId(resultSet.getInt(1));
                tool.setToolName(resultSet.getString(2));
                tool.setIsExpensive(resultSet.getBoolean(3));
                tool.setNumberOfAvailable(resultSet.getInt(4));
                tool.setCompanyId(resultSet.getInt(5));
                tool.setDepartmentId(resultSet.getInt(6));
                tool.setCompany(companyMap.get(tool.getCompanyId()));
                tool.setDepartment(department);

                tools.add(tool);
            }

            companyDao.close();
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            if (companyDao != null) companyDao.close();

            if (resultSet != null && !resultSet.isClosed()) resultSet.close();

            if (statement != null && !statement.isClosed()) statement.close();

            throw e;
        }

        return tools;
    }

    @Override
    public List<Tool> getToolsByCompany(Company company, int startIndex, int size) throws SQLException {
        List<Tool> tools = new LinkedList<Tool>();

        String sql = "select * from tool where departmentid = " + company.getId() + " limit " + startIndex + "," + size + ";";

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        Map<Integer, Department> departmentMap = new HashMap<Integer, Department>();

        Statement statement = null;

        ResultSet resultSet = null;

        Tool tool = null;

        try {
            statement = connection.createStatement();

            statement.execute(sql);

            for(Department department : departmentDao.getDepartments()){
                departmentMap.put(department.getId(), department);
            }

            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                tool = new Tool();

                tool.setId(resultSet.getInt(1));
                tool.setToolName(resultSet.getString(2));
                tool.setIsExpensive(resultSet.getBoolean(3));
                tool.setNumberOfAvailable(resultSet.getInt(4));
                tool.setCompanyId(resultSet.getInt(5));
                tool.setDepartmentId(resultSet.getInt(6));
                tool.setCompany(company);
                tool.setDepartment(departmentMap.get(tool.getDepartmentId()));

                tools.add(tool);
            }

            departmentDao.close();
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            if (departmentDao != null) resultSet.close();

            if (resultSet != null && !resultSet.isClosed()) resultSet.close();

            if (statement != null && !statement.isClosed()) statement.close();

            throw e;
        }

        return tools;
    }

    @Override
    public int getTotalOfTool(Department department) throws SQLException {

        String sql = "select count(*) from tool where departmentid = " + department.getId() + ";";

        int count = 0;

        Statement statement = null;

        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if(resultSet.next()){
                count = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {

            if (resultSet != null && !resultSet.isClosed()) resultSet.close();

            if (statement != null && !statement.isClosed()) statement.close();

            throw e;
        }

        return count;
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
    public void close() {
        try {
            if(connection != null && !connection.isClosed())connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed()) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
