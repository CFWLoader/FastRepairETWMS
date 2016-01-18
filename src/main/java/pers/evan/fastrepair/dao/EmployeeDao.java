package pers.evan.fastrepair.dao;

import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;

import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface EmployeeDao extends TemplateDao<Employee>{

    Employee getEmployeeByUsername(String username);

    List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize);

    List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize);

    int getTotalOfEmployeesByDepartment(Department department);

    int getTotalOfEmployeesByCompany(Company company);
}
