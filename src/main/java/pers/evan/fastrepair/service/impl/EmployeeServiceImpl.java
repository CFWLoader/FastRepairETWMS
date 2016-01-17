package pers.evan.fastrepair.service.impl;

import pers.evan.fastrepair.dao.EmployeeDao;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Employee employeeLogin(String idStr, String password) {

        Long id = Long.valueOf(idStr);

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
