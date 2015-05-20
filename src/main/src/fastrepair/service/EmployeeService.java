package fastrepair.service;

import fastrepair.model.Company;
import fastrepair.model.Department;
import fastrepair.model.Employee;

import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface EmployeeService {

    Long addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee employeeLogin(Long id, String password);

    Employee getEmployeeById(Long id);

    List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize);

    List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize);

    int getTotalOfEmployeesByDepartment(Department department);

    int getTotalOfEmployeesByCompany(Company company);

}
