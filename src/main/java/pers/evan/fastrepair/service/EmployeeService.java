package pers.evan.fastrepair.service;

import pers.evan.fastrepair.exception.UserNotFoundException;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;

import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface EmployeeService {

    long addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee employeeLogin(String username, String password) throws UserNotFoundException;

    Employee getEmployeeByUsername(String username) throws UserNotFoundException;

    Employee getEmployeeById(long id);

    List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize);

    List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize);

    int getTotalOfEmployeesByDepartment(Department department);

    int getTotalOfEmployeesByCompany(Company company);

    List<Employee> getEmployees(int startIndex, int pageSize);

    int getTotalOfEmployees();
}
