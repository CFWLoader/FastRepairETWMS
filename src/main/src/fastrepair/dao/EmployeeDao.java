package fastrepair.dao;

import fastrepair.exception.BadUpdateQueryException;
import fastrepair.exception.UserNotFoundException;
import fastrepair.model.Company;
import fastrepair.model.Department;
import fastrepair.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface EmployeeDao {

    int addEmployee(Employee employee, String password) throws SQLException;

    void updateEmployee(Employee employee, String password) throws BadUpdateQueryException, SQLException;

    void removeEmployee(Employee employee) throws BadUpdateQueryException;

    Employee getEmployee(int id, String password) throws BadUpdateQueryException, UserNotFoundException;

    Employee getEmployeeById(int id) throws BadUpdateQueryException, UserNotFoundException;

    List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize);

    List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize);

    int getTotalOfEmployeeByDepartment(Department department) throws SQLException;

    int getTotalOfEmployeeByCompany(Company company) throws SQLException;

    void close() throws SQLException;
}
