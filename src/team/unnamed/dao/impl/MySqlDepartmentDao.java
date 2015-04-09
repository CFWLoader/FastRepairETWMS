package team.unnamed.dao.impl;

import team.unnamed.dao.DepartmentDao;
import team.unnamed.model.Department;
import team.unnamed.util.MySqlConnectionManager;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlDepartmentDao implements DepartmentDao{

    private Connection connection;

    public MySqlDepartmentDao() {
        connection = MySqlConnectionManager.getDefaultConnection();
    }

    @Override
    public void addDepartment(Department department) throws SQLException {

        String sql = "insert into department values(null, ?);";

        PreparedStatement statement;

        try{
            statement = connection.prepareStatement(sql);

            statement.setString(1, department.getDepartmentType());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateDepartment(Department department) throws SQLException {

        String sql = "update department set departmenttype = ? where id = ?;";

        PreparedStatement statement;

        try{
            statement = connection.prepareStatement(sql);

            statement.setString(1, department.getDepartmentType());
            statement.setInt(2, department.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void removeDepartment(Department department) throws SQLException {

        String sql = "delete from department where id = ?;";

        PreparedStatement statement;

        try{
            statement = connection.prepareStatement(sql);

            statement.setInt(1, department.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Department> getDepartments() throws SQLException {
        List<Department> departmentSet = new LinkedList<Department>();
        Department department = null;

        String sql = "select * from department;";

        Statement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            while (resultSet.next()){
                department = new Department();
                department.setId(resultSet.getInt(1));
                department.setDepartmentType(resultSet.getString(2));

                departmentSet.add(department);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally{
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return departmentSet;
    }

    @Override
    public void close() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
