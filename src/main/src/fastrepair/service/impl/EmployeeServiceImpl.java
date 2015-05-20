package fastrepair.service.impl;

import fastrepair.dao.EmployeeDao;
import fastrepair.model.Company;
import fastrepair.model.Department;
import fastrepair.model.Employee;
import fastrepair.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

/**
 * Created by cfwloader on 5/20/15.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public Long addEmployee(Employee employee) {

        employeeDao.addEntity(employee);

        return employee.getId();

    }

    @Override
    public void updateEmployee(Employee employee) {

        employeeDao.updateEntity(employee);

    }

    @Override
    public void deleteEmployee(Employee employee) {

        employeeDao.deleteEntity(employee);

    }

    @Override
    public Employee employeeLogin(Long id, String password) {
        return (Employee) employeeDao.login(id, password);
    }

    @Override
    public Employee getEmployeeById(Long id) {

        return employeeDao.getEntityById(id);

    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize) {
        return employeeDao.getEmployeesByDepartment(department, startIndex, pageSize);
    }

    @Override
    public List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize) {
        return null;
    }

    @Override
    public int getTotalOfEmployeesByDepartment(Department department) {
        return 0;
    }

    @Override
    public int getTotalOfEmployeesByCompany(Company company) {
        return 0;
    }
}
