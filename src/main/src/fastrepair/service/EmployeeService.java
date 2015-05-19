package fastrepair.service;

import fastrepair.exception.BadRequestParameterException;
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
public interface EmployeeService {

    int addEmployee(Employee employee, String password) throws SQLException, BadRequestParameterException;

    void updateEmployee(Employee employee, String password) throws BadUpdateQueryException, SQLException;

    void removeEmployee(Employee employee) throws BadUpdateQueryException;

    Employee employeeLogin(String idStr, String password) throws BadUpdateQueryException, UserNotFoundException, BadRequestParameterException;

    Employee getEmployeeById(String idStr) throws BadRequestParameterException, BadUpdateQueryException, UserNotFoundException;

    List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize) throws SQLException;

    List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize) throws SQLException;

    int getTotalOfEmployeesByDepartment(Department department) throws SQLException;

    int getTotalOfEmployeesByCompany(Company company) throws SQLException;

    Employee employeeObjectAssembler(String idStr,
                                     String firstName,
                                     String lastName,
                                     String gender,
                                     String phone,
                                     String address,
                                     String companyIdStr,
                                     String departmentIdStr) throws BadUpdateQueryException, UserNotFoundException;

    void close() throws SQLException;
}
