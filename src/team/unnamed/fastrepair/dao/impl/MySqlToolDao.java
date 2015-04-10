package team.unnamed.fastrepair.dao.impl;

import team.unnamed.fastrepair.dao.CompanyDao;
import team.unnamed.fastrepair.dao.DepartmentDao;
import team.unnamed.fastrepair.dao.ToolDao;
import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.model.Tool;
import team.unnamed.fastrepair.util.MySqlConnectionManager;

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
    public void addTool(Tool tool) {

        String sql = "insert into tool values(null, ?, ?, ?, ?);";

        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, tool.getToolType());
            statement.setInt(2, tool.getNumberOfAvailable());
            statement.setInt(3, tool.getCompanyId());
            statement.setInt(4, tool.getDepartmentId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tool getToolById(int id) throws SQLException {

        String sql = "select * from tool where id = " + id + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        Tool tool = null;

        try {
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                tool = new Tool();

                tool.setId(resultSet.getInt(1));
                tool.setToolType(resultSet.getString(2));
                tool.setNumberOfAvailable(resultSet.getInt(3));
                tool.setCompanyId(resultSet.getInt(4));
                tool.setDepartmentId(resultSet.getInt(5));
            }

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
    public void updateTool(Tool tool) {

    }

    @Override
    public void removeTool(Tool tool) {

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
                tool.setToolType(resultSet.getString(2));
                tool.setNumberOfAvailable(resultSet.getInt(3));
                tool.setCompanyId(resultSet.getInt(4));
                tool.setDepartmentId(resultSet.getInt(5));
                tool.setCompany(companyMap.get(tool.getCompanyId()));
                tool.setDepartment(department);

                tools.add(tool);
            }

            companyDao.close();
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            if (companyDao != null) resultSet.close();

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
                tool.setToolType(resultSet.getString(2));
                tool.setNumberOfAvailable(resultSet.getInt(3));
                tool.setCompanyId(resultSet.getInt(4));
                tool.setDepartmentId(resultSet.getInt(5));
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

        String sql = "select count(*) from tool where department = " + department.getId() + ";";

        int count = 0;

        Statement statement = null;

        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();

            statement.executeUpdate(sql);

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
