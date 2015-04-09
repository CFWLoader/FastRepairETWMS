package team.unnamed.dao;

import team.unnamed.exception.BadUpdateQueryException;
import team.unnamed.exception.UserNotFoundException;
import team.unnamed.model.Company;
import team.unnamed.model.Department;
import team.unnamed.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface EmployeeDao {

    void addEmployee(Employee employee, String password) throws SQLException;

    void updateEmployee(Employee employee, String password) throws BadUpdateQueryException, SQLException;

    void removeEmployee(Employee employee) throws BadUpdateQueryException;

    Employee getEmployee(int id, String password) throws BadUpdateQueryException, UserNotFoundException;

    List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize);

    List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize);

    void close() throws SQLException;
}
