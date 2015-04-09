package team.unnamed.dao.impl;

import team.unnamed.dao.EmployeeDao;
import team.unnamed.exception.BadUpdateQueryException;
import team.unnamed.exception.UserNotFoundException;
import team.unnamed.model.Company;
import team.unnamed.model.Department;
import team.unnamed.model.Employee;
import team.unnamed.util.MySqlConnectionManager;

import java.sql.*;
import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlEmployeeDao implements EmployeeDao {

    Connection connection;

    public MySqlEmployeeDao() {
        this.connection = MySqlConnectionManager.getDefaultConnection();
    }

    @Override
    public void addEmployee(Employee employee, String password) throws SQLException {

        String sql = "insert into employee values(null, ?, ?, ?, ?, ?, ?, ?, password(?));";

        PreparedStatement statement;

        try{
            statement = connection.prepareStatement(sql);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getAddress());
            statement.setInt(6, employee.getCompanyId());
            statement.setInt(7, employee.getDepartmentId());
            statement.setString(8, password);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateEmployee(Employee employee, String password) throws BadUpdateQueryException, SQLException {

        if(employee.getId() < 0)throw new BadUpdateQueryException();

        String sql = "update employee set firstname = ?, lastname = ?, gender = ?, phone = ?, address = ?, companyid = ?, departmentid = ? where id = ?;";

        PreparedStatement statement;

        try{
            statement = connection.prepareStatement(sql);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getAddress());
            statement.setInt(6, employee.getCompanyId());
            statement.setInt(7, employee.getDepartmentId());
            statement.setInt(8, employee.getId());

            statement.executeUpdate();

            if(password != null){
                statement.executeUpdate("update employee set passwd = password('" + password + "') where id = " + employee.getId() + ";");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void removeEmployee(Employee employee) {

    }

    @Override
    public Employee getEmployee(int id, String password) throws BadUpdateQueryException, UserNotFoundException {

        if(id < 0)throw new BadUpdateQueryException();

        String sql = "select * from employee where id = " + id  + " and passwd = password('" + password +"');";

        Employee employee = null;

        Statement statement;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            if(resultSet.next()){

                employee = new Employee();

                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setGender(resultSet.getString(4));
                employee.setPhone(resultSet.getString(5));
                employee.setAddress(resultSet.getString(6));
                employee.setCompanyId(resultSet.getInt(7));
                employee.setDepartmentId(resultSet.getInt(8));
            }else{
                resultSet.close();
                statement.close();
                throw new UserNotFoundException();
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize) {
        return null;
    }

    @Override
    public List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize) {
        return null;
    }

    @Override
    public void close() {

    }
}
