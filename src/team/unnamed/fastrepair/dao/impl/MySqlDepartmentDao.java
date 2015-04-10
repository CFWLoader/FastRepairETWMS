package team.unnamed.fastrepair.dao.impl;

import team.unnamed.fastrepair.dao.DepartmentDao;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.util.MySqlConnectionManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlDepartmentDao implements DepartmentDao {

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
    public Department getDepartmentById(int id) throws SQLException {
        Department department = null;

        String sql = "select * from department where id = " + id + ";";

        Statement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if (resultSet.next()){
                department = new Department();
                department.setId(resultSet.getInt(1));
                department.setDepartmentType(resultSet.getString(2));
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

        return department;
    }

    @Override
    public void close() throws SQLException {
        try {
            if(connection != null && !connection.isClosed())connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed()) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
