package team.unnamed.fastrepair.service;

import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.exception.BadUpdateQueryException;
import team.unnamed.fastrepair.exception.UserNotFoundException;
import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.model.Employee;

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
