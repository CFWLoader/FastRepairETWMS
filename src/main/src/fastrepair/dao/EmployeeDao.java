package fastrepair.dao;

import fastrepair.model.Company;
import fastrepair.model.Department;
import fastrepair.model.Employee;

import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface EmployeeDao extends TemplateDao<Employee>{

    Employee login(Long id, String password);

    List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize);

    List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize);

    int getTotalOfEmployeesByDepartment(Department department);

    int getTotalOfEmployeesByCompany(Company company);
}
