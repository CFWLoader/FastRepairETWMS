package team.unnamed.fastrepair.dao.impl;

import team.unnamed.fastrepair.exception.BadUpdateQueryException;
import team.unnamed.fastrepair.exception.UserNotFoundException;
import team.unnamed.fastrepair.dao.CompanyDao;
import team.unnamed.fastrepair.dao.DepartmentDao;
import team.unnamed.fastrepair.dao.EmployeeDao;
import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.model.Employee;
import team.unnamed.fastrepair.util.MySqlConnectionManager;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlEmployeeDao implements EmployeeDao {

    private Connection connection;

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
    public void removeEmployee(Employee employee) throws BadUpdateQueryException {

        if(employee.getId() < 0)throw new BadUpdateQueryException();

        String sql = "delete from employee where id = " + employee.getId() + ";";

        Statement statement;

        try{
            statement = connection.createStatement();

            statement.executeUpdate(sql);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployee(int id, String password) throws BadUpdateQueryException, UserNotFoundException {

        if(id < 0)throw new BadUpdateQueryException();

        String sql = "select * from employee as e join department d on (e.departmentid = d.id) join company c on (e.companyid = c.id) where e.id = " + id  + " and passwd = password('" + password +"');";

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

                employee.setDepartment(new Department());
                employee.getDepartment().setId(resultSet.getInt(10));
                employee.getDepartment().setDepartmentType(resultSet.getString(11));

                employee.setCompany(new Company());
                employee.getCompany().setId(resultSet.getInt(12));
                employee.getCompany().setCompanyName(resultSet.getString(13));
                employee.getCompany().setLocation(resultSet.getString(14));

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
    public Employee getEmployeeById(int id) throws BadUpdateQueryException, UserNotFoundException {
        if(id < 0)throw new BadUpdateQueryException();

        String sql = "select * from employee as e join department d on (e.departmentid = d.id) join company c on (e.companyid = c.id) where e.id = " + id +";";

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

                employee.setDepartment(new Department());
                employee.getDepartment().setId(resultSet.getInt(10));
                employee.getDepartment().setDepartmentType(resultSet.getString(11));

                employee.setCompany(new Company());
                employee.getCompany().setId(resultSet.getInt(12));
                employee.getCompany().setCompanyName(resultSet.getString(13));
                employee.getCompany().setLocation(resultSet.getString(14));

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

        List<Employee> employees = new LinkedList<Employee>();

        CompanyDao companyDao = new MySqlCompanyDao();

        Map<Integer, Company> companyMap = new HashMap<Integer, Company>();

        String sql = "select * from employee where departmentid = " + department.getId() + "  limit " + startIndex + "," + pageSize + ";";

        Statement statement;

        try {

            Employee employee = null;

            statement = connection.createStatement();

            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            for(Company company : companyDao.getCompanies()){
                companyMap.put(company.getId(), company);
            }

            while(resultSet.next()){

                employee = new Employee();

                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setGender(resultSet.getString(4));
                employee.setPhone(resultSet.getString(5));
                employee.setAddress(resultSet.getString(6));
                employee.setCompanyId(resultSet.getInt(7));
                employee.setDepartmentId(resultSet.getInt(8));

                employee.setDepartment(department);

                employee.setCompany(companyMap.get(employee.getCompanyId()));

                employees.add(employee);
            }

            companyDao.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize) {
        List<Employee> employees = new LinkedList<Employee>();

        Map<Integer, Department> departmentMap = new HashMap<Integer, Department>();

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        String sql = "select * from employee where companyid = " + company.getId() + "  limit " + startIndex + "," + pageSize + ";";

        Statement statement;

        try {

            Employee employee = null;

            statement = connection.createStatement();

            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            for(Department department : departmentDao.getDepartments()){
                departmentMap.put(department.getId(), department);
            }

            while(resultSet.next()){

                employee = new Employee();

                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setGender(resultSet.getString(4));
                employee.setPhone(resultSet.getString(5));
                employee.setAddress(resultSet.getString(6));
                employee.setCompanyId(resultSet.getInt(7));
                employee.setDepartmentId(resultSet.getInt(8));

                employee.setDepartment(departmentMap.get(employee.getDepartmentId()));

                employee.setCompany(company);

                employees.add(employee);
            }

            departmentDao.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public int getTotalOfEmployeeByDepartment(Department department) throws SQLException {

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
    public int getTotalOfEmployeeByCompany(Company company) throws SQLException {

        String sql = "select count(*) from tool where departmentid = " + company.getId() + ";";

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
            }
        }
    }

}
